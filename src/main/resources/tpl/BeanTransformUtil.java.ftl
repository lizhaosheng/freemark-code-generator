package ${dopackage};

/**
 * <p>${title}</p>
 * 类名:${className}DO<br>
 * 创建人:${author}<br>
 * 创建时间:${createTime}<br>
 */
public class ${className}BeanTransformUtil {

    /**************************************** DO 2 VO ******************************************/
    public static ${className}VO to${className}VO(${className}DO d){
        ${className}VO vo = new ${className}VO();
        
        <#list props as property>
        vo.set${property.javaName?cap_first}(d.get${property.javaName?cap_first}());
        </#list>
        
        return vo;
    }
    /**************************************** VO 2 DO ******************************************/
    public static ${className}DO to${className}DO(${className}VO vo){
        ${className}DO d = new ${className}DO();
        
        <#list props as property>
        d.set${property.javaName?cap_first}(vo.get${property.javaName?cap_first}());
        </#list>
        
        return d;
    }
    /**************************************** VO 2 DTO ******************************************/
    public static ${className}DTO to${className}DTO(${className}VO vo){
        ${className}DTO dto = new ${className}DTO();
        
        <#list props as property>
        dto.set${property.javaName?cap_first}(vo.get${property.javaName?cap_first}());
        </#list>
        
        return dto;
    }
    /**************************************** DTO 2 DO ******************************************/
    public static ${className}DO to${className}DO(${className}DTO dto){
        ${className}DO d = new ${className}DO();
        
        <#list props as property>
        d.set${property.javaName?cap_first}(dto.get${property.javaName?cap_first}());
        </#list>
        
        return d;
    }
    /**************************************** DO 2 DTO ******************************************/
    public static ${className}DTO to${className}DTO(${className}DO d){
        ${className}DTO dto = new ${className}DTO();
        
        <#list props as property>
        dto.set${property.javaName?cap_first}(d.get${property.javaName?cap_first}());
        </#list>
        
        return dto;
    }
    /**************************************** DTO 2 VO ******************************************/
    public static ${className}VO to${className}VO(${className}DTO dto){
        ${className}VO vo = new ${className}VO();
        
        <#list props as property>
        vo.set${property.javaName?cap_first}(dto.get${property.javaName?cap_first}());
        </#list>
        
        return vo;
    }

}