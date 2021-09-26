package com.msb.dongbao.protal.web.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

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
    public String getTest(String appId,String name,String sign){
        //使用map是为了进行排序
        HashMap <String,String> map=new HashMap<>();
        map.put("appId",appId);
        map.put("name",name);

        String s = CheckUtils.generatorSign(map);
        return s.equals(sign)?"验证通过":"验证不通过";
    }
}
