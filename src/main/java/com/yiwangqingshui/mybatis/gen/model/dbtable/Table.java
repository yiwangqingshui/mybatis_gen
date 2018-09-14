package com.yiwangqingshui.mybatis.gen.model.dbtable;

import java.util.List;

/**
 * 数据库表信息
 * @author smc
 * @date 2018-09-11 08:46
 * @since
 */
public class Table {

    private String tableName;

    private String typeName;

    private String remarks;

    private List<Column> columnList;

    private PrimaryKeys primaryKeys;

    private UniqueIndex uniqueIndex;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    public PrimaryKeys getPrimaryKeys() {
        return primaryKeys;
    }

    public void setPrimaryKeys(PrimaryKeys primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

    public UniqueIndex getUniqueIndex() {
        return uniqueIndex;
    }

    public void setUniqueIndex(UniqueIndex uniqueIndex) {
        this.uniqueIndex = uniqueIndex;
    }
}
