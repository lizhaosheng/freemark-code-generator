package ${mapperpackage};

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;

/**
 * <p>${title}</p>
 * 类名:${className}Mapper<br>
 * 创建人:${author}<br>
 * 创建时间:${createTime}<br>
 */
 @Resource
public interface ${className}Mapper {
    
    int add(${className}DO model);

    ${className}DO getById(@Param("name")int id);
    
    List<${className}DO> query(${className}Query model);
    
    int deleteLogicById(@Param("name")int id);
    
    int deleteById(@Param("name")int id);
    
    int update(${className}DO model);
    
}