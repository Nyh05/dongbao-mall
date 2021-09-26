package com.msb.dongbao.protal.web.api;



import com.msb.dongbao.common.utill.MD5Util;
import com.msb.dongbao.common.utill.Sha256Utils;

import java.util.*;

public class CheckUtils {
    private static String appSecret="aaaa";
    //校验签名的方法
    public static boolean checkSign(SortedMap<String, String> map){
        Object sign = map.get("sign");
        map.remove("sign");
        String s = generatorSign(map);
        return sign.equals(s)?true:false;
    }
    //根据map生成签名
    public static String generatorSign(Map<String, String> map) {
        map.remove("sign");
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
        //return Sha256Utils.getSHA256(stringBuilder.toString());
    }

    static Map<String, String> sortMapByKey(Map<String, String> map) {

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
        Map<String,String> map =new HashMap<>();
        map.put("appId","1");
        map.put("name","2");
        map.put("urlParam","3");
        //map.put("timestamp",1632622101000L);
        Map<String, String> stringStringMap = sortMapByKey(map);
        System.out.println(stringStringMap);
        String s = generatorSign(map);
        System.out.println(s);//md5==730155771f2bf762870136daf5590f8e
        //sha256==62bdcbdc9070719e4fef716e1ce0f596fa2c875a554e74db8f7a530245ac3c2a
    }
}
