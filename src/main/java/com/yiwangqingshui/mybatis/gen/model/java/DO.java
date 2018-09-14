package com.yiwangqingshui.mybatis.gen.model.java;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 这里写代码描述
 *
 * @author smc
 * @date 2018-09-10 21:18
 * @since
 **/
public class DO  extends Base{

    private List<Fields>  fieldses = Lists.newArrayList();

    public List<Fields> getFieldses() {
        return fieldses;
    }

    public void setFieldses(List<Fields> fieldses) {
        this.fieldses = fieldses;
    }

    public void addFieldses(Fields filelds){
        fieldses.add(filelds);
    }




}
