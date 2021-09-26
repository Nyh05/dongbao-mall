package com.msb.dongbao.protal.web.api;



import com.msb.dongbao.common.utill.MD5Util;

import java.util.*;

public class CheckUtils {
    private static String appSecret="aaaa";
    //根据map生成签名
    public static String generatorSign(HashMap<String, String> map) {
        //排序
        Map<String,String> stringObjectMap=sortMapByKey(map);
        //转格式，可以形成一长串的字符串，方便进行加密生成签名
        Set<Map.Entry<String, String>> entries = stringObjectMap.entrySet();
        StringBuilder stringBuilder=new StringBuilder();
        for (Map.Entry<String, String> entry : entries) {
            String s = entry.getKey() + "," + entry.getValue();
            stringBuilder.append(s).append("#");
        }
        //追加一个secret，然后生成签名
        stringBuilder.append("secret").append(appSecret);
        return MD5Util.md5(stringBuilder.toString());
    }

    static Map<String, String> sortMapByKey(HashMap<String, String> map) {

        Map<String,String> sortMap=new TreeMap<String,String>(new MyCompator());
        sortMap.putAll(map);
        return sortMap;
    }
    static class MyCompator implements Comparator<String>{

        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    public static void main(String[] args) {
        HashMap<String,String> map =new HashMap<>();
        map.put("appId","1");
        map.put("name","张三");
        Map<String, String> stringStringMap = sortMapByKey(map);
        System.out.println(stringStringMap);
        String s = generatorSign(map);
        System.out.println(s);//730155771f2bf762870136daf5590f8e
    }
}
