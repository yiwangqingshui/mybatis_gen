package com.yiwangqingshui.mybatis.gen.exec.impl;

import com.google.common.collect.Lists;
import com.yiwangqingshui.mybatis.gen.enums.JdbcAndJavaTypeMapEnum;
import com.yiwangqingshui.mybatis.gen.exec.BaseExec;
import com.yiwangqingshui.mybatis.gen.model.Gen;
import com.yiwangqingshui.mybatis.gen.model.dbtable.Column;
import com.yiwangqingshui.mybatis.gen.model.dbtable.PrimaryKeys;
import com.yiwangqingshui.mybatis.gen.model.dbtable.Table;
import com.yiwangqingshui.mybatis.gen.model.dbtable.UniqueIndex;
import com.yiwangqingshui.mybatis.gen.utils.CamelCaseUtils;
import com.yiwangqingshui.mybatis.gen.utils.ConfigUtil;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这里写代码描述
 *
 * @author smc
 * @date 2018-09-10 20:44
 * @since
 **/
public class OracleExec implements BaseExec {


    /**
     * mysql表字段名称
     */
    private static final String COL_COLUMN_NAME = "COLUMN_NAME";

    /**
     * 字段数据类型
     */
    private static final String COL_DATA_TYPE = "DATA_TYPE";

    /**
     * 主键索引名称
     */
    private static final String PRIMARY_KEY = "PRIMARY";

    private Map<String, Column> columnNameColumnMap = new HashMap<>();

    private Map<String, Column> primaryKeyCache = new HashMap<>();

    @Override
    public String code() {
        return "oracle";
    }

    @Override
    public void exec(Gen gen, Connection connection) {
        Table table = new Table();
        ansTable(table, connection);
        table.setColumnList(ansColumn(connection));
        createPrimaryKey(table, connection);
        createUniqueIndex(table, connection);
        gen.setTable(table);

    }

    /**
     * 获取表的描述信息
     *
     * @param table
     * @param connection
     */
    private void ansTable(Table table, Connection connection) {
        final PreparedStatement pstmt;
        setTableJavaTypeName(table);
        try {
            pstmt = connection.prepareStatement("select * from  user_tab_comments");
            final ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                if (ConfigUtil.getCmd().equalsIgnoreCase(tableName)) {
                    String remarks = resultSet.getString("COMMENTS");
                    remarks = StringUtils.isNotBlank(remarks)?remarks:"";
                    table.setRemarks(remarks);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setTableJavaTypeName(Table table) {
        /**
         * 取消表的前缀生成实体类
         */
        String tablePrefix = ConfigUtil.getMybatisGenMojo().getTablePrefix().toLowerCase();
        int tablePrefixIndex = ConfigUtil.getCmd().toLowerCase().indexOf(tablePrefix);
        String tableName = ConfigUtil.getCmd();
        table.setTableName(tableName);
        if (tablePrefixIndex == 0) {
            tableName = ConfigUtil.getCmd().toLowerCase().substring(tablePrefix.length());
        }
        table.setTypeName(CamelCaseUtils.toCapitalizeCamelCase(tableName));

    }

    private List<Column> ansColumn(Connection connection) {
        List<Column> columns = Lists.newArrayList();
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getColumns(null, null, ConfigUtil.getCmd(), null);
            while (resultSet.next()) {
                Column column = new Column();
                String columName = resultSet.getString(COL_COLUMN_NAME);
                column.setColumnName(columName.toUpperCase());
                column.setJavaName(CamelCaseUtils.toCamelCase(columName));
                int dataType = resultSet.getInt(COL_DATA_TYPE);
                /**
                 * oracle number对应的java是DECIMAL，使用场景的需要进行强转为long类型
                 */
                if(dataType ==3){
                    dataType = -5;
                }
                JDBCType jdbcType = JDBCType.valueOf(dataType);
                column.setSqlType(jdbcType.getName());
                column.setJavaType(JdbcAndJavaTypeMapEnum.getJavaTypeByJdbcType(jdbcType.getName()).getJavaType());
                String remarks = getTableColumnRemarks(columName.toUpperCase(), connection);
                column.setRemarks(remarks);
                columnNameColumnMap.put(columName, column);
                columns.add(column);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columns;
    }

    private String getTableColumnRemarks(String columnName, Connection connection) {
        String sql = String.format("select COMMENTS from all_col_comments where table_name = '%s' and column_name = '%s'", ConfigUtil.getCmd(), columnName);
        String remarks = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                remarks = resultSet.getString("COMMENTS");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return StringUtils.isNotBlank(remarks)?remarks:" ";
    }


    public void createUniqueIndex(Table table, Connection connection) {
        try {

            UniqueIndex uniqueIndex = new UniqueIndex();
            ResultSet resultSet = connection.getMetaData().getIndexInfo(null, null, ConfigUtil.getCmd(), true, false);
            while (resultSet.next()) {
                String columnName = resultSet.getString("COLUMN_NAME");
                if (StringUtils.isNotBlank(columnName)) {
                    Column column = columnNameColumnMap.get(columnName);
                    /**
                     * 索引名称为主键
                     */
                    if (primaryKeyCache.get(columnName) == null) {
                        uniqueIndex.addColumn(column);
                    }
                    column.setUniqueIndex(true);
                    columnNameColumnMap.put(columnName, column);
                }
            }
            table.setUniqueIndex(uniqueIndex);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void createPrimaryKey(Table table, Connection connection) {
        PrimaryKeys primaryKeys = new PrimaryKeys();
        try {
            ResultSet resultSet = connection.getMetaData().getPrimaryKeys(null, null, ConfigUtil.getCmd());
            while (resultSet.next()) {
                String columnName = resultSet.getString("COLUMN_NAME");
                Column column = columnNameColumnMap.get(columnName);
                column.setUniqueIndex(true);
                columnNameColumnMap.put(columnName, column);
                primaryKeys.addColumn(column);
                primaryKeyCache.put(columnName, column);
            }
            table.setPrimaryKeys(primaryKeys);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
