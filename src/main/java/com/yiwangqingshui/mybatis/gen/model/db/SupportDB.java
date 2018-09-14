package com.yiwangqingshui.mybatis.gen.model.db;

/**
 * 这里写文档描述
 *
 * @author smc
 * @date 2018-09-10 16:05
 * @since
 */
public enum SupportDB {

    /**
     * mysql
     */
    MYSQL("mysql"),

    /**
     * oracle
     */
    ORACLE("oracle");

    private String name;

    SupportDB(String name){
        this.name = name;
    }

    public static String getName(String name){
        for (SupportDB supportDB : SupportDB.values()){
            if (supportDB.getName().equalsIgnoreCase(name)){
                return name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
