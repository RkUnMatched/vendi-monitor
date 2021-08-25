package com.vendixxx.monitor.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class JsonUtil {

    private JsonUtil() {
        //
    }

    public static String toJson(Object obj) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.fromJson(json, clazz);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        Gson gson = new Gson();
        return gson.fromJson(json, typeOfT);
    }

    public static <T> List<T> toList(String json, Class clazz) {
        Type type = new ParameterizedTypeImpl(clazz);
        return new Gson().fromJson(json, type);
    }


    public static <T> List<Map<String, T>> toListMap(String json, Class<T> clazz) {
        Gson gson = new Gson();
        List<Map<String, JsonObject>> mapList = gson.fromJson(json,
                new TypeToken<List<Map<String, JsonObject>>>() {
                }.getType());
        List<Map<String, T>> result = new ArrayList<>();
        mapList.stream().map(map -> map.keySet().stream()
                .collect(Collectors.toMap(key -> key, key -> gson.fromJson(map.get(key), clazz), (a, b) -> b)))
                .forEach(result::add);
        return result;
    }

    public static <T> Map<String, T> toMap(String json) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(json, new TypeToken<Map<String, T>>() {
            }.getType());
        } catch (Exception e) {
            log.error("parseJson error:{}, e:{}", json, e);
            return new HashMap<>();
        }
    }

    public static <T> T fromMap(Map<String, Object> map, Class<T> type) {
        try {
            Gson gson = new Gson().newBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            return gson.fromJson(gson.toJson(map), type);
        } catch (Exception e) {
            log.error("parseJson error:{}, e:{}", map, e);
            return null;
        }
    }


    private static class ParameterizedTypeImpl implements ParameterizedType {

        Class clazz;

        public ParameterizedTypeImpl(Class clz) {
            clazz = clz;
        }

        @Override
        public Type[] getActualTypeArguments() {
            //返回实际类型组成的数据
            return new Type[]{clazz};
        }

        @Override
        public Type getRawType() {
            //返回原生类型，即HashMap
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            //返回Type对象
            return null;
        }
    }

}
