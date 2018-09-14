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

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mysql数据库元数据解析
 * @author smc
 * @date 2018-09-10 20:41
 * @since
 **/
public class MysqlExec implements BaseExec {

    /**
     * mysql表字段名称
     */
    private static final String COL_COLUMN_NAME="COLUMN_NAME";

    /**
     * 字段数据类型
     */
    private static final String COL_DATA_TYPE="DATA_TYPE";

    /**
     * 字段备注
     */
    private static final String COL_REMARKS="REMARKS";

    /**
     *  字段是否是自增
     */
    private static final String COL_IS_AUTOINCREMENT="IS_AUTOINCREMENT";

    /**
     * 主键索引名称
     */
    private static final String PRIMARY_KEY="PRIMARY";

    private Map<String,Column> columnNameColumnMap = new HashMap<>();

    @Override
    public String code() {
        return "mysql";
    }

    @Override
    public void exec(Gen gen, Connection connection) {
        Table table = new Table();
        ansTable(table,connection);
        table.setColumnList(ansColumn(connection));
        createUniqueIndex(table,connection);
        gen.setTable(table);
    }

    /**
     * 获取表的描述信息
     * @param table
     * @param connection
     */
    private void ansTable(Table table,Connection connection){
        final PreparedStatement pstmt;
        setTableJavaTypeName(table);
        try {
            pstmt = connection.prepareStatement("show table status");
            final ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                 String tableName = resultSet.getString("NAME");
                if(ConfigUtil.getCmd().equalsIgnoreCase(tableName)){
                    table.setRemarks(resultSet.getString("COMMENT"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setTableJavaTypeName(Table table){
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

    private List<Column> ansColumn(Connection connection){
        List<Column> columns = Lists.newArrayList();
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getColumns(null,null,ConfigUtil.getCmd(),null);
            while (resultSet.next()){
                Column column = new Column();
                String columName= resultSet.getString(COL_COLUMN_NAME);
                column.setColumnName(columName.toUpperCase());
                column.setJavaName(CamelCaseUtils.toCamelCase(columName));
                int dataType = resultSet.getInt(COL_DATA_TYPE);
                JDBCType jdbcType =  JDBCType.valueOf(dataType);
                column.setSqlType(jdbcType.getName());
                column.setJavaType(JdbcAndJavaTypeMapEnum.getJavaTypeByJdbcType(jdbcType.getName()).getJavaType());
                String remarks = resultSet.getString(COL_REMARKS);
                column.setRemarks(remarks);
                String autoIncrement = resultSet.getString(COL_IS_AUTOINCREMENT);
                column.setAutoIncrement("YES".equalsIgnoreCase(autoIncrement)?true:false);
                columnNameColumnMap.put(columName,column);
                columns.add(column);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columns;
    }


    public void createUniqueIndex(Table table,Connection connection){
        try {
            PrimaryKeys primaryKeys= new PrimaryKeys();
            UniqueIndex uniqueIndex = new UniqueIndex();
            ResultSet resultSet = connection.getMetaData().getIndexInfo(null,null,ConfigUtil.getCmd(),true,false);
            while (resultSet.next()) {
                String indexName = resultSet.getString("INDEX_NAME");
                String columnName = resultSet.getString("COLUMN_NAME");
                Column column = columnNameColumnMap.get(columnName);
                /**
                 * 索引名称为主键
                 */
                if(PRIMARY_KEY.equalsIgnoreCase(indexName)){
                    primaryKeys.addColumn(column);
                }else{
                    uniqueIndex.addColumn(column);
                }
                column.setUniqueIndex(true);
                columnNameColumnMap.put(columnName,column);
            }
            table.setPrimaryKeys(primaryKeys);
            table.setUniqueIndex(uniqueIndex);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
