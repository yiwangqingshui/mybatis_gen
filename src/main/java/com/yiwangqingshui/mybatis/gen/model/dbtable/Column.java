package com.yiwangqingshui.mybatis.gen.model.dbtable;

/**
 * 数据库字段信息
 * @author smc
 * @date 2018-09-11 08:47
 * @since
 */
public class Column {

    /**
     * 字段类型
     */
    private String sqlType;


    /**
     * java对应类型
     */
    private String javaType;

    /**
     * 字段名称
     */
    private String columnName;

    /**
     * java名称
     */
    private String javaName;

    /**
     * 是否是自增字段
     */
    private boolean autoIncrement;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 默认数据
     */
    private String defaultVal;

    /**
     * 是否为唯一索引，唯一索引不更新该字段
     */
    private boolean uniqueIndex;


    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getJavaName() {
        return javaName;
    }

    public void setJavaName(String javaName) {
        this.javaName = javaName;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDefaultVal() {
        return defaultVal;
    }

    public void setDefaultVal(String defaultVal) {
        this.defaultVal = defaultVal;
    }

    public boolean isUniqueIndex() {
        return uniqueIndex;
    }

    public void setUniqueIndex(boolean uniqueIndex) {
        this.uniqueIndex = uniqueIndex;
    }

}
