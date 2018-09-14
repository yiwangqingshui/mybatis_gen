package com.yiwangqingshui.mybatis.gen.model.java;

import com.google.common.collect.Lists;

import java.util.List;

public class UniqueIndexFields {

    private List<Fields> fieldsList = Lists.newArrayList();

    public List<Fields> getFieldsList() {
        return fieldsList;
    }

    public void setFieldsList(List<Fields> fieldsList) {
        this.fieldsList = fieldsList;
    }

    public  void addFilelds(Fields filelds){
        fieldsList.add(filelds);
    }

}
