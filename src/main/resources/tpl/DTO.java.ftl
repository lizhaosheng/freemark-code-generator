package ${dopackage};

/**
 * <p>${title}</p>
 * 类名:${className}DTO<br>
 * 创建人:${author}<br>
 * 创建时间:${createTime}<br>
 */
public class ${className}DTO implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    <#list props as property>
    
    /**
     * ${property.remark}
     */
    private ${property.javaType} ${property.javaName};
    </#list>
    
    <#list props as property>
    
    public ${property.javaType} get${property.javaName?cap_first}() {
        return ${property.javaName};
    }
    
    public void set${property.javaName?cap_first}(${property.javaType} ${property.javaName}) {
        this.${property.javaName} = ${property.javaName};
    }
    </#list>

}