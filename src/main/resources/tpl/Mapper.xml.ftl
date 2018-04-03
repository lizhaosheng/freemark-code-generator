<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE mapper PUBLIC
    "-//mybatis.org//DTD Mapper 3.0//EN"
    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    
<mapper namespace="${mapperpackage}.${className}Mapper">
    <resultMap id="${className}Map" type="${dopackage}.${className}DO">
        <#list props as property>
        <result property="${property.javaName}" column="${property.colName}" jdbcType="${property.colType}" />
        </#list>
    </resultMap>
    
    <sql id="${tableName}_col">
        <#list props as property>
        ${property.colName}<#if property_index+1 < props?size>,</#if>
        </#list>
    </sql>

    <!-- 添加 -->
    <insert id="add" parameterType="${dopackage}.${className}DO" useGeneratedKeys="true" keyProperty="id">
        insert into ${tableName}(
            <#list props as property>
            ${property.colName}<#if property_index+1 < props?size>,</#if>
            </#list>
        )
        values(
            <#list props as property>
            ${r"#{"}${property.javaName}, jdbcType=${property.colType}}<#if property_index+1 < props?size>,</#if>
            </#list>
        )
    </insert>
    
    <!-- 根据id获取 -->
    <select id="getById" parameterType="java.lang.Integer" resultMap="${className}Map" >
        select
            <include refid="${tableName}_col"></include>
        from
            ${tableName}
        where
            del = 0 and id = ${r"#{"}id}
    </select>
    
    <!-- 根据条件查询 -->
    <select id="query" parameterType="${dopackage}.${className}Query" resultMap="${className}Map" >
        select
            <include refid="${tableName}_col"></include>
        from
            ${tableName}
        <where>
        <#list props as property>
        <if test="${property.javaName} != null <#if property.javaType == "String">and ${property.javaName} != ''</#if>">
            and ${property.colName} = ${r"#{"}${property.javaName}}
        </if>
        </#list>
        </where>
    </select>
    
    <!-- 逻辑删除记录 -->
    <update id="deleteLogicById" parameterType="java.lang.Integer">
        update
            ${tableName}
        set
            del = 1
        where 
            id = ${r"#{"}id}
    </update>
    
    <!-- 物理删除记录 -->
    <delete id="deleteById" parameterType="java.lang.Integer">
        delete from
            ${tableName}
        where 
            id = ${r"#{"}id}
    </delete>
    
    <!-- 修改 -->
    <update id="update" parameterType="${dopackage}.${className}DO">
        update 
            ${tableName}
        set
        <#list props as property>
        <if test="${property.javaName} != null <#if property.javaType == "String">and ${property.javaName} != ''</#if>">
            ${property.colName} = ${r"#{"}${property.javaName}},
        </if>
        </#list>
            gmt_update = now()
        where
            id = ${r"#{"}id}
  </update>
</mapper>