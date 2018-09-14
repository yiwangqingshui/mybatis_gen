package com.yiwangqingshui.mybatis.gen.model.dbtable;

import com.google.common.collect.Lists;

import java.util.List;


public class PrimaryKeys {

    private List<Column> columnList = Lists.newArrayList();

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    public void addColumn(Column column){
        columnList.add(column);
    }
}
