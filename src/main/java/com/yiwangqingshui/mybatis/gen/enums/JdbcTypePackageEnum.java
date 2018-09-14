package com.yiwangqingshui.mybatis.gen.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 这里写代码描述
 *
 * @author smc
 * @date 2018-09-11 21:14
 * @since
 **/
public enum JdbcTypePackageEnum {

    /**
     *日期格式
     */
    DATETIME("DATETIME","java.util.Date"),

    TIME("TIME","java.sql.Time"),

    DATE("DATE","java.util.Date"),

    TIMESTAMP("TIMESTAMP","java.util.Date"),

    DECIMAL("DECIMAL","java.math.BigDecimal")
    ;


    private String type;

    private String packageName;


    JdbcTypePackageEnum(String type, String packageName){
        this.type = type;
        this.packageName = packageName;
    }

    private static Map<String,String> typeToPackage = new HashMap<>();

    static{
        for(JdbcTypePackageEnum javaTypePackageEnum :  JdbcTypePackageEnum.values()){
            typeToPackage.put(javaTypePackageEnum.getType(),javaTypePackageEnum.getPackageName());
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public  static String getPackageNmaeType(String type){
        return typeToPackage.get(type);
    }


}
