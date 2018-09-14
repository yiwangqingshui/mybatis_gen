<@pp.dropOutputFile />
<#if gen.xmlMapper?? >
<#assign xmlMapper =gen.xmlMapper>
<#assign table  = gen.table>
<#assign doMapper =gen.doMapper>
<@pp.changeOutputFile name = "/src/main/resources/${xmlMapper.xmlPath}/${xmlMapper.doMapper.className}.xml" />
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${xmlMapper.doMapper.packageName}.${xmlMapper.doMapper.className}">

  <resultMap id="BaseResultMap"  type="${xmlMapper.doObject.packageName}.${xmlMapper.doObject.className}">
       <#list table.columnList as column>
             <result column="${column.columnName}" property="${column.javaName}" jdbcType="${column.sqlType}" javaType="${column.javaType}"/>
       </#list>
  </resultMap>

   <sql id="Base_Column_List">
        <#list table.columnList as column><#if column_index gt 0>,</#if>${column.columnName}</#list>
   </sql>

   <insert id="insert" parameterType="${xmlMapper.doObject.packageName}.${xmlMapper.doObject.className}">
       insert into ${table.tableName}(
           <#assign t_insert_name = 0>
           <#list table.columnList as column>
               <#if column.autoIncrement?string('yes', 'no')=='no'>
                  <#if t_insert_name gt 0 >
                      ,${column.columnName}
                  <#else>
                      <#assign t_insert_name = 1>
                       ${column.columnName}
                  </#if>
               </#if>
           </#list> ) values(
                <#assign t_insert_val = 0>
             <#list table.columnList as column>
                     <#if column.autoIncrement?string('yes', 'no')=='no'>
                        <#if t_insert_val gt 0 >
                      ,${"#"}{${column.javaName},jdbcType=${column.sqlType}}
                        <#else>
                            <#assign t_insert_val = 1>
                      ${"#"}{${column.javaName},jdbcType=${column.sqlType}}
                        </#if>
                     </#if>
             </#list>  )
   </insert>

<#if doMapper.primaryKeyFields?? >
  <#list doMapper.primaryKeyFields.fieldsList as fields>
   <update id="updateBy${fields.name?cap_first}" parameterType="${xmlMapper.doObject.packageName}.${xmlMapper.doObject.className}">
       <#assign c_idx = 0>
        update ${table.tableName} set
            <#list table.columnList as column>
                  <#if column.autoIncrement?string('yes', 'no')=='no'  && column.javaName !=fields.name && column.uniqueIndex?string('yes','no')=='no' >
                         <#if c_idx gt 0 >
                              ,${column.columnName}= ${"#"}{${column.javaName},jdbcType=${column.sqlType} }
                         <#else>
                               ${column.columnName}= ${"#"}{${column.javaName},jdbcType=${column.sqlType} }
                               <#assign c_idx =1>
                         </#if>
                  </#if>
            </#list>
        where ${fields.sqlName} = ${"#"}{${fields.name},jdbcType=${fields.sqlType}}
   </update>

    <delete id="deleteBy${fields.name?cap_first}" parameterType="${fields.javaType}">
          delete from ${table.tableName} where ${fields.sqlName} = ${"#"}{${fields.name},jdbcType=${fields.sqlType}}
    </delete>

    <select id="getBy${fields.name?cap_first}" resultMap="BaseResultMap" parameterType="${fields.javaType}">
        select <include refid="Base_Column_List"/>  from  ${table.tableName}  where ${fields.sqlName} = ${"#"}{${fields.name},jdbcType=${fields.sqlType}}
    </select>
  </#list>
</#if>

<#if doMapper.uniqueIndexFields?? >
  <#list doMapper.uniqueIndexFields.fieldsList as fields>
   <update id="updateByIndex${fields.name?cap_first}" parameterType="${xmlMapper.doObject.packageName}.${xmlMapper.doObject.className}">
       <#assign c_idx = 0>
        update ${table.tableName} set
            <#list table.columnList as column>
                  <#if column.autoIncrement?string('yes', 'no')=='no'  && column.javaName !=fields.name && column.uniqueIndex?string('yes','no')=='no' >
                         <#if c_idx gt 0 >
                              ,${column.columnName}= ${"#"}{${column.javaName},jdbcType=${column.sqlType} }
                         <#else>
                               ${column.columnName}= ${"#"}{${column.javaName},jdbcType=${column.sqlType} }
                               <#assign c_idx =1>
                         </#if>
                  </#if>
            </#list>
        where ${fields.sqlName} = ${"#"}{${fields.name},jdbcType=${fields.sqlType}}
   </update>

    <delete id="deleteByIndex${fields.name?cap_first}" parameterType="${fields.javaType}">
          delete from ${table.tableName} where ${fields.sqlName} = ${"#"}{${fields.name},jdbcType=${fields.sqlType}}
    </delete>

    <select id="getByIndex${fields.name?cap_first}" resultMap="BaseResultMap" parameterType="${fields.javaType}">
        select <include refid="Base_Column_List"/>  from  ${table.tableName}  where ${fields.sqlName} = ${"#"}{${fields.name},jdbcType=${fields.sqlType}}
    </select>
  </#list>
</#if>

 <select id="queryTotal" parameterType="${xmlMapper.doObject.packageName}.${xmlMapper.doObject.className}" resultType="Integer">
        select count(*) from   ${table.tableName}
          <trim prefix="where" prefixOverrides="and">
               <#list table.columnList as column>
                     <if test="${column.javaName} != null">
                        and ${column.columnName}= ${"#"}{${column.javaName},jdbcType=${column.sqlType} }
                     </if>
               </#list>
          </trim>
 </select>

 <select id="queryList" parameterType="${xmlMapper.doObject.packageName}.${xmlMapper.doObject.className}" resultMap="BaseResultMap">
        select <include refid="Base_Column_List"/>  from  ${table.tableName}
        <trim prefix="where" prefixOverrides="and">
               <#list table.columnList as column>
                      <if test="${column.javaName} != null">
                          and ${column.columnName}= ${"#"}{${column.javaName},jdbcType=${column.sqlType} }
                      </if>
               </#list>
        </trim>
 </select>
 <#if gen.page??>
 <#assign page =gen.page>
    <select id="queryPage" parameterType="${page.basePackageName}.Page" resultMap="BaseResultMap">

       select * from (
          select r.*,rownum rn  from (
          select <include refid="Base_Column_List"/>  from  ${table.tableName}
                <if test="condition != null">
                <trim prefix="where" prefixOverrides="and">
                       <#list table.columnList as column>
                              <if test="condition.${column.javaName} != null">
                                  and ${column.columnName}= ${"#"}{condition.${column.javaName},jdbcType=${column.sqlType} }
                              </if>
                       </#list>
                </trim>
                </if>
             ) r where rownum  <![CDATA[ <= ]]>  ${"#"}{endPosition}
          ) t where t.rn <![CDATA[ > ]]> ${"#"}{startPosition}
    </select>
 </#if>

 <select id="queryAll" resultMap="BaseResultMap">
      select <include refid="Base_Column_List"/>  from  ${table.tableName}
 </select>

</mapper>
</#if>
