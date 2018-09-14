package com.yiwangqingshui.mybatis.gen.dataloaders;

import com.yiwangqingshui.mybatis.gen.exec.TableAnalysisUtil;
import com.yiwangqingshui.mybatis.gen.model.Gen;
import com.yiwangqingshui.mybatis.gen.utils.ConfigUtil;

import java.sql.Connection;

/**
 * 这里写文档描述
 *
 * @author smc
 * @date 2018-09-10 16:35
 * @since
 */
public class GenAbstractDataLoader extends AbstractDataLoader {

    private static final String PAGE_FLAG="yes";

    @Override
    public void gen(Gen gen, Connection connection) {

        TableAnalysisUtil.ansTable(gen,connection);
        /**
         * 创建DO数据
         */
        TableAnalysisUtil.createDO(gen);
        if(PAGE_FLAG.equalsIgnoreCase(ConfigUtil.getMybatisGenMojo().getPageFlag())){
            TableAnalysisUtil.createPage(gen);
        }
        TableAnalysisUtil.createDOMapper(gen);
        TableAnalysisUtil.createXMLMapper(gen);
        TableAnalysisUtil.createDAO(gen);

    }



}
