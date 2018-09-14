package com.yiwangqingshui.mybatis.gen.model.java;

/**
 * 这里写文档描述
 * @author smc
 * @date 2018-09-12 16:52
 * @since
 */
public class XMLMapper{

    private  DOMapper doMapper;

    private DO doObject;
    /**
     * xml配置路径
     */
    private String xmlPath;

    private String insertSql;

    public DOMapper getDoMapper() {
        return doMapper;
    }

    public void setDoMapper(DOMapper doMapper) {
        this.doMapper = doMapper;
    }

    public String getXmlPath() {
        return xmlPath;
    }

    public void setXmlPath(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    public DO getDoObject() {
        return doObject;
    }

    public void setDoObject(DO doObject) {
        this.doObject = doObject;
    }

    public String getInsertSql() {
        return insertSql;
    }

    public void setInsertSql(String insertSql) {
        this.insertSql = insertSql;
    }
}
