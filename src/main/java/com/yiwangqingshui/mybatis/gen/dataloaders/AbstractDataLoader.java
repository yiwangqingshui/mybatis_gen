package com.yiwangqingshui.mybatis.gen.dataloaders;

import com.yiwangqingshui.mybatis.gen.datasource.DbFactory;
import com.yiwangqingshui.mybatis.gen.model.Gen;
import fmpp.Engine;
import fmpp.tdd.DataLoader;

import java.io.File;
import java.sql.Connection;
import java.util.List;

/**
 * 这里写文档描述
 *
 * @author smc
 * @date 2018-09-10 16:32
 * @since
 */
public abstract class AbstractDataLoader implements DataLoader {

    @Override
    public Object load(Engine engine, List list) throws Exception {
        Gen gen = new Gen();
        Connection connection =  null;
        try{
            connection = DbFactory.instance.getConnection();
            gen(gen,connection);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            connection.close();
        }
        return gen;
    }

    public abstract void gen(Gen gen, Connection connection);



}
