package com.yiwangqingshui.mybatis.gen.exec;

import com.yiwangqingshui.mybatis.gen.model.Gen;

import java.sql.Connection;

/**
 *
 * @author smc
 * @date 2018-09-10 20:38
 * @since
 **/
public abstract class AbstractBaseExec {

     abstract public String code();

     abstract public void exec(Gen gen, Connection connection);



}
