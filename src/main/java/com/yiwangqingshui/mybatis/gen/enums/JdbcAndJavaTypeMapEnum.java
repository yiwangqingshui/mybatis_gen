package com.yiwangqingshui.mybatis.gen.enums;


import com.yiwangqingshui.mybatis.gen.expection.DalgenException;

import java.util.HashMap;
import java.util.Map;

/**
 * jdbc类型向java类型的转换
 * @author smc
 * @date 2018-09-11
 * @since  1.0.0
 */
public enum JdbcAndJavaTypeMapEnum {
    /**
     * Char type map enum.
     */
    CHAR("CHAR", "String"),
    /**
     * Varchar type map enum.
     */
    VARCHAR("VARCHAR", "String"),
    /**
     * Longvarchar type map enum.
     */
    LONGVARCHAR("LONGVARCHAR", "String"),
    /**
     * Numeric type map enum.
     */
    NUMERIC("NUMERIC", "java.math.BigDecimal"),
    /**
     * Decimal type map enum.
     */
    DECIMAL("DECIMAL", "java.math.BigDecimal"),
    /**
     * Bit type map enum.
     */
    BIT("BIT", "Boolean"),
    /**
     * Tinyint type map enum.
     */
    TINYINT("TINYINT", "Integer"),
    /**
     * Smallint type map enum.
     */
    SMALLINT("SMALLINT", "Integer"),

    /**
     * Int type map enum.
     */
    INT("INT", "Integer"),

    /**
     * Integer type map enum.
     */
    INTEGER("INTEGER", "Integer"),
    /**
     * Bigint type map enum.
     */
    BIGINT("BIGINT", "Long"),
    /**
     * Real type map enum.
     */
    REAL("REAL", "Float"),
    /**
     * Float type map enum.
     */
    FLOAT("FLOAT", "Double"),
    /**
     * Double type map enum.
     */
    DOUBLE("DOUBLE", "Double"),
    /**
     * Binary type map enum.
     */
    BINARY("BINARY", "byte"),
    /**
     * Varbinary type map enum.
     */
    VARBINARY("VARBINARY", "byte"),

    /**
     * Longvarbinary type map enum.
     */
    LONGVARBINARY("LONGVARBINARY", "byte"),
    /**
     * Date type map enum.
     */
    DATE("DATE", "java.util.Date"),

    /**
     * Time type map enum.
     */
    TIME("TIME", "java.sql.Time"),

    DATETIME("DATETIME", "java.util.Date"),

    TIMESTAMP("TIMESTAMP", "java.util.Date"),

    OTHER("other", "other");

    /**
     * The Jdbc type.
     */
    //
    private String jdbcType;
    /**
     * The Java type.
     */
    private String javaType;

    /**
     * Instantiates a new Type map enum.
     *
     * @param jdbcType the jdbc type
     * @param javaType the java type
     */
    private JdbcAndJavaTypeMapEnum(String jdbcType, String javaType) {
        this.jdbcType = jdbcType;
        this.javaType = javaType;
    }

    private static Map<String, JdbcAndJavaTypeMapEnum> codeLookup = new HashMap<String, JdbcAndJavaTypeMapEnum>();

    static {
        for (JdbcAndJavaTypeMapEnum type : JdbcAndJavaTypeMapEnum.values()) {
            codeLookup.put(type.name(), type);
        }
    }

    public static JdbcAndJavaTypeMapEnum getJavaTypeByJdbcType(String jdbcType) {
        JdbcAndJavaTypeMapEnum type = codeLookup.get(jdbcType);
        if (type != null) {
            return type;
        }
        throw new DalgenException("类型转换错误:" + jdbcType);
    }

    /**
     * Gets by jdbc type.
     *
     * @param jdbcType the jdbc type
     * @return the by jdbc type
     */
    public static JdbcAndJavaTypeMapEnum getByJdbcTypeWithOther(String jdbcType) {
        JdbcAndJavaTypeMapEnum type = codeLookup.get(jdbcType);
        if (type != null) {
            return type;
        }
        return OTHER;
    }

    /**
     * Gets jdbc type.
     *
     * @return the jdbc type
     */
    public String getJdbcType() {
        return jdbcType;
    }

    /**
     * Gets java type.
     *
     * @return the java type
     */
    public String getJavaType() {
        return javaType;
    }
}
