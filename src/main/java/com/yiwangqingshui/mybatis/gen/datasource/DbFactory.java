package com.yiwangqingshui.mybatis.gen.datasource;

import com.yiwangqingshui.mybatis.gen.utils.ConfigUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 这里写代码描述
 *
 * @author smc
 * @date 2018-09-10 20:48
 * @since
 **/
public class DbFactory {


    public static DbFactory instance = new DbFactory();

    private DbFactory(){

    }

    public Connection getConnection(){
        try {
            Class.forName(ConfigUtil.getMybatisGenMojo().getDriverClass());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(ConfigUtil.getMybatisGenMojo().getUrl(),ConfigUtil.getMybatisGenMojo().getUsername(),ConfigUtil.getMybatisGenMojo().getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }




}
