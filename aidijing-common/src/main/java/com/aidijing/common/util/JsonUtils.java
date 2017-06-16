package com.aidijing.common.util;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;

/**
 * json工具类
 *
 * @author : 披荆斩棘
 * @date : 2016/10/2
 */
public abstract class JsonUtils {


    /**
     * <p>jackson</p>
     * <a href="https://github.com/FasterXML/jackson-docs">document</a>
     */
    private static final ObjectMapper MAPPER = new ObjectMapper().setDateFormat( new SimpleDateFormat( DateUtils.DATE_BASIC_STYLE ) );
    /**
     * <p>gson</p>
     * <a href="https://github.com/google/gson/blob/master/UserGuide.md">document</a>
     */
    private static final Gson         GSON   = new GsonBuilder().setDateFormat( DateUtils.DATE_BASIC_STYLE ).create();
    /**
     * <p>fastjson</p>
     * <a href="https://github.com/alibaba/fastjson/wiki/%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98">document</a>
     */
    private static JSON JSON;

    public static ObjectMapper getObjectMapper () {
        return MAPPER;
    }

    public static Gson getGson () {
        return GSON;
    }


    /**
     * 转换为Json
     * <p>默认使用Jackson进行转换</p>
     *
     * @param input
     * @return 如果转换失败返回 <code>null</code> ,否则返回转换后的json
     */
    public static String toJson ( Object input ) {
        try {
            return MAPPER.writeValueAsString( input );
        } catch ( JsonProcessingException e ) {
            LogUtils.getLogger().catching( e );
        }
        return null;
    }

    /**
     * json转换为指定类型
     * <p>默认使用Jackson进行转换</p>
     * 注意 : 指定类型是内部类会报错 jackson can only instantiate non-static inner class by using default, no-arg
     *
     * @param inputJson  : json
     * @param targetType : 目标类型
     * @param <T>
     * @return 如果解析失败返回 <code>null</code> ,否则返回解析后的json
     */
    public static < T > T jsonToType ( String inputJson, Class< T > targetType ) {
        try {
            return MAPPER.readValue( inputJson, targetType );
        } catch ( Exception e ) {
            LogUtils.getLogger().catching( e );
        }
        return null;
    }

    /**
     * json转换为指定类型(支持泛型)
     * <pre class="code">
     * 示例 :
     * ResponseEntity< User > responseEntity = JsonUtils.jsonToType( jscksonJsonValue,new TypeReference< ResponseEntity< User > >() {} );
     * </pre>
     *
     * @param inputJson  : json
     * @param targetType : 目标类型
     * @param <T>
     * @return
     */
    public static < T > T jsonToType ( String inputJson, TypeReference targetType ) {
        try {
            return MAPPER.readValue( inputJson, targetType );
        } catch ( Exception e ) {
            LogUtils.getLogger().catching( e );
        }
        return null;
    }


}
