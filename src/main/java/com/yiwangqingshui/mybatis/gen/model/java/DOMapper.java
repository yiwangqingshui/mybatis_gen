package com.yiwangqingshui.mybatis.gen.model.java;

/**
 * domapper模板数据
 * @author smc
 * @date 2018-09-12 11:06
 * @since
 */
public class DOMapper extends DO {

    private DO doObject;

    private PrimaryKeyFields primaryKeyFields;

    private UniqueIndexFields uniqueIndexFields;

    public DO getDoObject() {
        return doObject;
    }

    public void setDoObject(DO doObject) {
        this.doObject = doObject;
    }

    public PrimaryKeyFields getPrimaryKeyFields() {
        return primaryKeyFields;
    }

    public void setPrimaryKeyFields(PrimaryKeyFields primaryKeyFields) {
        this.primaryKeyFields = primaryKeyFields;
    }

    public UniqueIndexFields getUniqueIndexFields() {
        return uniqueIndexFields;
    }

    public void setUniqueIndexFields(UniqueIndexFields uniqueIndexFields) {
        this.uniqueIndexFields = uniqueIndexFields;
    }
}
