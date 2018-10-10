package com.yf.orderService.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @Package com.yf.orderService.utils
 * @Description: JsonUtils
 * @author: jingh
 * @date 2018/10/10 15:18
 */
public class JsonUtils {
    private static final  ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 字符串 转 JsonNode
     * @param str
     * @return
     */
    public static JsonNode str2JsonNode(String str){

        try {
            return objectMapper.readTree(str);
        } catch (IOException e) {
            return null;
        }
    }
}
