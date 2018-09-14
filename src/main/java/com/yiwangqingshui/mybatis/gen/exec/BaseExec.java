package com.yiwangqingshui.mybatis.gen.exec;

import com.yiwangqingshui.mybatis.gen.model.Gen;

import java.sql.Connection;

/**
 * 这里写代码描述
 *
 * @author smc
 * @date 2018-09-10 20:38
 * @since
 **/
public interface BaseExec {

     public String code();

     public void exec(Gen gen, Connection connection);

}
