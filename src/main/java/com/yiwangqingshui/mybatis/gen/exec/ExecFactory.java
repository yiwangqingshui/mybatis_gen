package com.yiwangqingshui.mybatis.gen.exec;

import com.yiwangqingshui.mybatis.gen.exec.impl.MysqlExec;
import com.yiwangqingshui.mybatis.gen.exec.impl.OracleExec;

import java.util.HashMap;
import java.util.Map;

/**
 * 这里写代码描述
 *
 * @author smc
 * @date 2018-09-10 20:40
 * @since
 **/
public class ExecFactory {


    public static  ExecFactory instance = new ExecFactory();

    private Map<String,BaseExec> execMap = new HashMap<>();

    private ExecFactory(){
        MysqlExec mysqlExec = new MysqlExec();
        OracleExec oracleExec = new OracleExec();
        execMap.put(mysqlExec.code(),mysqlExec);
        execMap.put(oracleExec.code(),oracleExec);
    }

    public BaseExec loadExec(String code){
        return execMap.get(code.toLowerCase());
    }

}
