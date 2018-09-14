package com.yiwangqingshui.mybatis.gen.exec;

import com.yiwangqingshui.mybatis.gen.enums.JdbcTypePackageEnum;
import com.yiwangqingshui.mybatis.gen.model.Gen;
import com.yiwangqingshui.mybatis.gen.model.dbtable.Column;
import com.yiwangqingshui.mybatis.gen.model.dbtable.PrimaryKeys;
import com.yiwangqingshui.mybatis.gen.model.dbtable.Table;
import com.yiwangqingshui.mybatis.gen.model.dbtable.UniqueIndex;
import com.yiwangqingshui.mybatis.gen.model.java.*;
import com.yiwangqingshui.mybatis.gen.utils.ConfigUtil;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;

/**
 * 数据库表逆向分析
 * @author smc
 * @date 2018-09-10 21:34
 * @since 1.0.0
 **/
public class TableAnalysisUtil {

    private static final String PAGE_FLAG="yes";
    /**
     * 根据数据库类型分析metadata
     *
     * @param gen
     * @param connection
     */
    public static void ansTable(Gen gen, Connection connection) {
        BaseExec baseExec = ExecFactory.instance.loadExec(ConfigUtil.getMybatisGenMojo().getDbType());
        baseExec.exec(gen, connection);
    }

    /**
     * 生成DO的数据
     *
     * @param gen
     */
    public static void createDO(Gen gen) {
        DO doObject = new DO();
        Table table = gen.getTable();
        doObject.setClassName(table.getTypeName() + "DO");
        doObject.setClassPath(ConfigUtil.getDistPackageName() + "/dataobject");
        doObject.setPackageName(ConfigUtil.getMybatisGenMojo().getPackageName() + ".dataobject");
        doObject.setDesc(table.getRemarks());
        for (Column column : table.getColumnList()) {
            Fields filelds = new Fields();
            filelds.setDesc(column.getRemarks());
            filelds.setJavaType(column.getJavaType().substring(column.getJavaType().lastIndexOf(".") + 1));
            filelds.setName(column.getJavaName());
            doObject.addFieldses(filelds);
            String packageName = JdbcTypePackageEnum.getPackageNmaeType(column.getSqlType());
            if (StringUtils.isNotBlank(packageName)) {
                doObject.addImportLists(packageName);
            }
        }
        gen.setDoObject(doObject);
    }

    /**
     * 创建分页数据
     *
     * @param gen
     */
    public static void createPage(Gen gen) {
        Page page = new Page();
        page.setBaseClassPath(ConfigUtil.getDistPackageName() + "/page");
        page.setBasePackageName(ConfigUtil.getMybatisGenMojo().getPackageName() + ".page");
        gen.setPage(page);
    }

    /**
     * 创建mapper对象
     *
     * @param gen
     */
    public static void createDOMapper(Gen gen) {
        Table table = gen.getTable();
        DOMapper doMapper = new DOMapper();
        doMapper.setClassPath(ConfigUtil.getDistPackageName() + "/mapper");
        doMapper.setClassName(table.getTypeName() + "Mapper");
        doMapper.setPackageName(ConfigUtil.getMybatisGenMojo().getPackageName() + ".mapper");
        DO doObject = gen.getDoObject();
        doMapper.setDoObject(doObject);
        doMapper.addImportLists(doObject.getPackageName() + "." + doObject.getClassName());
        if(PAGE_FLAG.equalsIgnoreCase(ConfigUtil.getMybatisGenMojo().getPageFlag())){
            doMapper.addImportLists(gen.getPage().getBasePackageName() + ".Page");
        }
        createDOMapperPrimaryKey(doMapper, gen.getTable().getPrimaryKeys());
        createDOMapperUniqueIndex(doMapper, gen.getTable().getUniqueIndex());
        gen.setDoMapper(doMapper);
    }

    private static void createDOMapperPrimaryKey(DOMapper doMapper, PrimaryKeys primaryKeys) {
        PrimaryKeyFields primaryKeyFields = new PrimaryKeyFields();
        primaryKeys.getColumnList().forEach(column -> {
            Fields fields = new Fields();
            int javaTypeIndex = column.getJavaType().lastIndexOf(".");
            if (javaTypeIndex > -1) {
                doMapper.addImportLists(column.getJavaType());
            }
            fields.setJavaType(column.getJavaType().substring(javaTypeIndex + 1));
            fields.setName(column.getJavaName());
            fields.setSqlName(column.getColumnName());
            fields.setSqlType(column.getSqlType());
            primaryKeyFields.addFilelds(fields);
        });
        doMapper.setPrimaryKeyFields(primaryKeyFields);
    }

    private static void createDOMapperUniqueIndex(DOMapper doMapper, UniqueIndex uniqueIndexs) {
        UniqueIndexFields uniqueIndexFields = new UniqueIndexFields();
        uniqueIndexs.getColumnList().forEach(column -> {
            Fields fields = new Fields();
            int javaTypeIndex = column.getJavaType().lastIndexOf(".");
            if (javaTypeIndex > -1) {
                doMapper.addImportLists(column.getJavaType());
            }
            fields.setJavaType(column.getJavaType().substring(javaTypeIndex + 1));
            fields.setName(column.getJavaName());
            fields.setSqlName(column.getColumnName());
            fields.setSqlType(column.getSqlType());
            uniqueIndexFields.addFilelds(fields);
        });
        doMapper.setUniqueIndexFields(uniqueIndexFields);
    }

    /**
     * 创建mapperxml数据库语句文件
     * @param gen
     */
    public static void createXMLMapper(Gen gen) {
        XMLMapper xmlMapper = new XMLMapper();
        DO doObject = gen.getDoObject();
        xmlMapper.setDoObject(doObject);
        xmlMapper.setDoMapper(gen.getDoMapper());
        xmlMapper.setXmlPath(gen.getDoMapper().getClassPath() + "/xml");
        gen.setXmlMapper(xmlMapper);
    }


    public static void createDAO(Gen gen){
        DAO dao = new DAO();
        Table table = gen.getTable();
        DOMapper doMapper = gen.getDoMapper();
        dao.setDoMapper(doMapper);
        dao.setClassPath(ConfigUtil.getDistPackageName() + "/dao");
        dao.setClassName(table.getTypeName() + "DAO");
        dao.setPackageName(ConfigUtil.getMybatisGenMojo().getPackageName() + ".dao");
        dao.addImportLists(doMapper.getPackageName()+"."+doMapper.getClassName());
        dao.addAllImportList(doMapper.getImportLists());
        gen.setDao(dao);
    }

}
