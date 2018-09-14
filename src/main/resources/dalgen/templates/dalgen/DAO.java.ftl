<@pp.dropOutputFile />
<#if gen.dao??>
<#assign dao =gen.dao>
    <@pp.changeOutputFile name = "/src/main/java/${dao.classPath}/${dao.className}.java" />
package ${dao.packageName};

import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;

<#list dao.importLists as import>
import ${import};
</#list>

/**
 *  dao处理
 */
@Repository
public class ${dao.className} {

    @Resource
    private ${dao.doMapper.className} ${dao.doMapper.className?uncap_first};

    public int insert(${dao.doMapper.doObject.className} ${dao.doMapper.doObject.className?uncap_first}){
        return ${dao.doMapper.className?uncap_first}.insert(${dao.doMapper.doObject.className?uncap_first});
    }


  <#list dao.doMapper.primaryKeyFields.fieldsList as fields>
    public int deleteBy${fields.name?cap_first}(${fields.javaType} ${fields.name}){
        return ${dao.doMapper.className?uncap_first}.deleteBy${fields.name?cap_first}(${fields.name});
    }

    public ${dao.doMapper.doObject.className} getBy${fields.name?cap_first}(${fields.javaType} ${fields.name}){
        return ${dao.doMapper.className?uncap_first}.getBy${fields.name?cap_first}(${fields.name});
    }

    public int updateBy${fields.name?cap_first}(${dao.doMapper.doObject.className} ${dao.doMapper.doObject.className?uncap_first}){
        return ${dao.doMapper.className?uncap_first}.updateBy${fields.name?cap_first}(${dao.doMapper.doObject.className?uncap_first});
    }

  </#list>

   <#list dao.doMapper.uniqueIndexFields.fieldsList as fields>
    public int deleteByIndex${fields.name?cap_first}(${fields.javaType} ${fields.name}){
        return ${dao.doMapper.className?uncap_first}.deleteByIndex${fields.name?cap_first}(${fields.name});
    }

    public ${dao.doMapper.doObject.className} getByIndex${fields.name?cap_first}(${fields.javaType} ${fields.name}){
        return ${dao.doMapper.className?uncap_first}.getByIndex${fields.name?cap_first}(${fields.name});
    }

    public int updateByIndex${fields.name?cap_first}(${dao.doMapper.doObject.className} ${dao.doMapper.doObject.className?uncap_first}){
        return ${dao.doMapper.className?uncap_first}.updateByIndex${fields.name?cap_first}(${dao.doMapper.doObject.className?uncap_first});
    }
    </#list>

    public int queryTotal(${dao.doMapper.doObject.className} ${dao.doMapper.doObject.className?uncap_first}){
        return ${dao.doMapper.className?uncap_first}.queryTotal(${dao.doMapper.doObject.className?uncap_first});
    }

      <#if gen.page??>
    public Page<${dao.doMapper.doObject.className}> queryPage(Page<${dao.doMapper.doObject.className}> page){

        int total = queryTotal(page.getCondition());
        List<${dao.doMapper.doObject.className}> lists = ${dao.doMapper.className?uncap_first}.queryPage(page);
        page.setTotalCount(total);
        page.setDatas(lists);
        return page;
    }
      </#if>

    public List<${dao.doMapper.doObject.className}> queryList(${dao.doMapper.doObject.className} ${dao.doMapper.doObject.className?uncap_first}){
        return ${dao.doMapper.className?uncap_first}.queryList(${dao.doMapper.doObject.className?uncap_first});
    }

    public List<${dao.doMapper.doObject.className}> queryAll(){
        return ${dao.doMapper.className?uncap_first}.queryAll();
    }
}

</#if>
