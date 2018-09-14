package com.yiwangqingshui.mybatis.gen.model.java;

/**
 */
public class Fields {


    /**
     * The Java type.
     */
    private String javaType;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Desc.
     */
    private String desc;

    private String sqlName;

    private String sqlType;

    /**
     * Gets java type.
     *
     * @return the java type
     */
    public String getJavaType() {
        return javaType;
    }

    /**
     * Sets java type.
     *
     * @param javaType the java type
     */
    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets desc.
     *
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets desc.
     *
     * @param desc the desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getSqlName() {
        return sqlName;
    }

    public void setSqlName(String sqlName) {
        this.sqlName = sqlName;
    }

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }
}
