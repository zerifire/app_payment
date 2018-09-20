package com.towerchain.appPayment.utils;

import com.google.gson.Gson;

import java.util.Map;

/**
 * Created by TowerChain_T01 on 2018/9/12.
 */
public class JsonUtility {

    private JsonUtility() {
    }

    /**
     * 对象转换成json
     *
     * @param object
     * @return
     */
    public static String obj2Json(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    /**
     * json转换成对象
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T json2Object(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }

    /**
     * 对象转换成map
     * @param o
     * @return
     */
    public static Map objToMap(Object o){
        return JsonUtility.json2Object(JsonUtility.obj2Json(o), Map.class);
    }


}
