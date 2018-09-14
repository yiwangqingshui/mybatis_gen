package com.yiwangqingshui.mybatis.gen.model;


import com.yiwangqingshui.mybatis.gen.model.dbtable.Table;
import com.yiwangqingshui.mybatis.gen.model.java.*;

/**
 * 生成对象的数据
 * @author smc
 * @date 2018-09-10 14:38
 * @since 1.0.0
 */
public class Gen {

    private DO doObject;

    private Table table;

    private Page page;

    private DOMapper doMapper;

    private XMLMapper xmlMapper;

    private DAO dao;

    public DO getDoObject() {
        return doObject;
    }

    public void setDoObject(DO doObject) {
        this.doObject = doObject;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public DOMapper getDoMapper() {
        return doMapper;
    }

    public void setDoMapper(DOMapper doMapper) {
        this.doMapper = doMapper;
    }

    public XMLMapper getXmlMapper() {
        return xmlMapper;
    }

    public void setXmlMapper(XMLMapper xmlMapper) {
        this.xmlMapper = xmlMapper;
    }

    public DAO getDao() {
        return dao;
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }
}
