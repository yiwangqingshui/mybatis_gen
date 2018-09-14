package com.yiwangqingshui.mybatis.gen.utils;

import com.yiwangqingshui.mybatis.gen.MybatisGenMojo;

/**
 * 这里写文档描述
 * @author smc
 * @date 2018-09-10 17:08
 * @since
 */
public class ConfigUtil {

    private static MybatisGenMojo  mybatisGenMojo;

    private static String cmd;

    private static String packageName;


    public static void setMojo(MybatisGenMojo mybatisGenMojo){
        ConfigUtil.mybatisGenMojo = mybatisGenMojo;
    }

    public static void setCmd(String cmd){
        ConfigUtil.cmd = cmd;
    }

    public static MybatisGenMojo getMybatisGenMojo(){
        return mybatisGenMojo;
    }

    public static String  getCmd(){
        return ConfigUtil.cmd;
    }

    public static void setPackageName(String sourcePackageName){

        ConfigUtil.packageName = sourcePackageName.replace(".", "/");
    }

    public static String getDistPackageName(){
        return ConfigUtil.packageName;
    }

}
