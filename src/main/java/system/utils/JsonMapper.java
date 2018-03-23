package system.utils;

import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.TypeReference;

/**
 * @Author: mol
 * @Description:对象类型与json数据相互转换工具类
 * @Date: create in 13:59 2018/2/27
 */
@Slf4j
public class JsonMapper {
    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        //config
        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS,false);
        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
    }

    /**
     * 将对象转化为json字符串
     */
    public  static <T> String object2String(T t){
        if(t == null){
            return null;
        }
        try {
            if(t instanceof String){
                return (String) t;
            }else{
                return objectMapper.writeValueAsString(t);
            }
        } catch (Exception e) {
            log.warn("【对象转换为字符串】类型转换异常，error:{}",e);
            return null;
        }
    }
    /**
     * 将json字符串转化为对象
     */
    public static <T> T string2Object(String src, TypeReference<T> tTypeReference){
        if(src == null || tTypeReference == null){
            return null;
        }
        try {
            if(tTypeReference.getType().equals(String.class)){
                return (T) src;
            }else{
                return objectMapper.readValue(src,tTypeReference);
            }
        }catch (Exception e){
            log.warn("【字符串转换为对象】类型转换异常,src:{},tTypeReference:{},error:{}",src,tTypeReference.getType(),e);
            return null;
        }
    }
}
