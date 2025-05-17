package com.javaweb.utils;

import java.util.Map;

public class MapUtil {
    public static <T> T getObject(Map<String,Object> params, Class<T> tClass, String key){
        Object obj = params.getOrDefault(key, null);
        if(obj != null){
            if(tClass.getTypeName().equals("java.lang.Long")){
                obj = obj != "" ? Long.valueOf(obj.toString()):null;
            }
            else if(tClass.getTypeName().equals("java.lang.Integer")){
                obj = obj != "" ? Integer.valueOf(obj.toString()):null;
            }
            else if(tClass.getTypeName().equals("java.lang.String")){
                obj =obj.toString(); //da khac null do co ham check roi
            }
            return tClass.cast(obj);
        }
        return null;
    }
}
