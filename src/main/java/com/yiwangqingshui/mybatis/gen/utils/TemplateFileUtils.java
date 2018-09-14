package com.yiwangqingshui.mybatis.gen.utils;

import com.yiwangqingshui.mybatis.gen.MybatisGenMojo;
import org.apache.commons.io.FileUtils;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 *  config文件处理工具类
 * @author smc
 * @date 2018-09-10 14:24
 * @since
 */
public class TemplateFileUtils {

    private static JarFile jarFile;

    private static final String templatePath="dalgen/templates/";

    /**
     * 验证是否是文件，包含.表示表示是文件
     */
    private static final String VALIDATE_TYPE=".";

    /**
     * 验证mapperxml的文件
     */
    private static final String VALIDATE_MAPPER_XML_TYPE="Mapper.xml";

    private static final String MYSQL_DB_TYPE="mysql";

    /**
     * 验证根据不同的db取
     */
    private static final String VALIDATE_MAPPER_TYPE_BY_DB="OXMLMapper";

    static {
        try {
            jarFile = new JarFile(TemplateFileUtils.class.getProtectionDomain().getCodeSource()
                    .getLocation().getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyFile(MybatisGenMojo mybatisGenMojo){
        Enumeration<JarEntry>  entryEnumeration = jarFile.entries();
        while (entryEnumeration.hasMoreElements()){
            JarEntry jarEntry =  entryEnumeration.nextElement();
            String fileName = jarEntry.getName();
            if(fileName.startsWith(templatePath)){
                copyAndWriteFile(fileName,mybatisGenMojo.getTemplateDirectory(),mybatisGenMojo.getDbType());
            }
        }
    }

    /**
     * 复制和写文件
     * @param soureName
     * @param outTemplateFile
     * @param dbType
     */
    private static void copyAndWriteFile(String soureName, File outTemplateFile,String dbType) {

        if(!outTemplateFile.exists()){
            outTemplateFile.mkdirs();
        }
        if(soureName.indexOf(VALIDATE_TYPE) ==-1){
            return;
        }
        File distFile= new File(outTemplateFile+soureName.substring(templatePath.length()-1));
        if (distFile.exists()){
            return ;
        }
        if(soureName.indexOf(VALIDATE_MAPPER_XML_TYPE)>=0){
            if(MYSQL_DB_TYPE.equalsIgnoreCase(dbType)){
                if(soureName.indexOf(VALIDATE_MAPPER_TYPE_BY_DB) ==-1){
                   writeFile(soureName,distFile);
                }
            }else{
                if(soureName.indexOf(VALIDATE_MAPPER_TYPE_BY_DB)>0){
                    writeFile(soureName,distFile);
                }
            }
        }else{
           writeFile(soureName,distFile);
        }
    }

    public static void writeFile(String sourceName,File distFile){
        InputStream inputStream = TemplateFileUtils.class.getResourceAsStream("/" + sourceName);
        try {
            FileUtils.copyToFile(inputStream,distFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
