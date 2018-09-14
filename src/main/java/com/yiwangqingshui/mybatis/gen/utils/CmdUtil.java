package com.yiwangqingshui.mybatis.gen.utils;


import java.util.Scanner;
/**
 * Created by bangis.wangdf on 15/12/3. Desc 控制台输入
 */
public class CmdUtil {


    public static String DALGEN_VERSION = "";

    /**
     * 获取控制台输入
     * 目前只支持一个数据库的使用
     * @return 控制台命令
     */
    public String consoleInput() {
        Scanner cmdIn = new Scanner(System.in);
        return chooseTableCmd(cmdIn);
    }

    private String chooseTableCmd(Scanner cmdIn) {
        System.out.println("===========输入需要生成的表==============");
        System.out.println("-- 多表用 ; (分号分隔)分隔");
        System.out.println("-- q 退出");
        System.out.println("===========输入需要生成的表==============");
        String _cmd = cmdIn.next();
        return _cmd;
    }

}
