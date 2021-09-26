package com.msb.dongbao.protal.web.api;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.msb.dongbao.protal.web.api.posttest.SignDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api-safe")
public class ApiSafeController {

    @GetMapping("/hello")
    public String hello(){
        return "hello api-safe";
    }
    /**
     * 测试 get 方法 参数防篡改
     * @return
     */
    @GetMapping("/get-test")
    public String getTest(String appId, String name, String sign, long timestamp, HttpServletRequest request){
        //使用map是为了进行排序
        HashMap <String,String> map=new HashMap<>();
        //参数写死了，应该从httprequest中获取需要加密的参数
        //MD5和sha256生成的密文都是不可逆的散列算法
        //sha256的碰撞几率远远小于MD5，所以它更安全
        /*map.put("appId",appId);
        map.put("name",name);
        map.put("timestamp",timestamp);*/

 /*       //添加时间戳，让签名有期限
        long time = System.currentTimeMillis() - timestamp;
        if (time>1000*120){
            return "接口过期了";
        }*/

        //从请求端接收参数
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            //获取name
            String s = parameterNames.nextElement();
            //获取值
            String parameter = request.getParameter(s);
            map.put(s,parameter);
        }
        System.out.println(map);
        String s = CheckUtils.generatorSign(map);
        System.out.println(s);
        return s.equals(sign)?"验证通过":"验证不通过";
    }
    @PostMapping("post-test")
    public static String postTest(@RequestBody SignDTO signDTO){
        System.out.println("进入了controller方法。。。。");
        //打印参数
        JSONObject obj = JSONUtil.parseObj(signDTO);
        System.out.println("controller参数是："+obj);

        //以下部分转至filter中处理.在checkutils中添加一个校验签名的类checkSign
        /*//参数转map
        Map<String, Object> kvMap = Convert.toMap(String.class,Object.class,obj);
        //map排序
        Map<String, Object> map = CheckUtils.sortMapByKey((Map<String, Object>) kvMap);
        //生成签名
        //客户端传来的sign
        Object sign = kvMap.get("sign");
        //自己生成的sign
        String generatorSign = CheckUtils.generatorSign((Map<String, Object>) map);
        //判断签名
        return sign.equals(generatorSign)?"验证通过":"验证不通过";*/
        return "controller";
    }
}
