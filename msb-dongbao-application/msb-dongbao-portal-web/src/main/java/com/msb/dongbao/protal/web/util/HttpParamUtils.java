package com.msb.dongbao.protal.web.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
@Slf4j
public class HttpParamUtils {
    public static SortedMap<String,String> getAllParam(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取url中的参数
        Map<String, String> urlParam = getUrlParam(request);
        //获取body中的参数
        Map<String, String> bodyParam = getBodyParam(request);
        //总的参数
        SortedMap<String,String> map =new TreeMap<>();
        for (Map.Entry<String, String> stringStringEntry : urlParam.entrySet()) {
            map.put(stringStringEntry.getKey(),stringStringEntry.getValue());
        }
        for (Map.Entry<String, String> stringStringEntry : bodyParam.entrySet()) {
            map.put(stringStringEntry.getKey(),stringStringEntry.getValue());
        }
        log.info("全部的参数为："+map);
        return map;
    }
    //获取url上的参数
    public static Map<String,String> getUrlParam(HttpServletRequest request){
        Map<String,String> map =new HashMap<>();
        String qs = request.getQueryString();//此处需要对url进行解码处理，因为传输中会进行编码如20%为空格
        String queryString = null;
        try {
            if (!StringUtils.isBlank(qs)){
                queryString = URLDecoder.decode(qs,"utf-8");
            }
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] split = queryString.split("&");
        for (String s : split) {
            int i = s.indexOf("=");
            map.put(s.substring(0,i),s.substring(i+1));
        }
        System.out.println("URL上的参数为："+map);
        return map;
    }
    //获取body中的参数
    public static Map<String,String> getBodyParam(HttpServletRequest request) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        Map map =null;
        if (reader!=null){
            StringBuilder sb=new StringBuilder();
            String str="";
            //读取流
            while ((str=reader.readLine())!=null){
                sb.append(str);
            }
            //转成map,有以下两种方式，一种是fastjson中的jsonobject
            /*cn.hutool.json.JSONObject jsonObject = JSONUtil.parseObj(sb.toString());
            Map<String, String> stringStringMap = Convert.toMap(String.class, String.class, jsonObject);*/
            map = JSONObject.parseObject(sb.toString(), Map.class);
            System.out.println("body参数是："+map);
            reader.close();
        }
        return map;
    }
}
