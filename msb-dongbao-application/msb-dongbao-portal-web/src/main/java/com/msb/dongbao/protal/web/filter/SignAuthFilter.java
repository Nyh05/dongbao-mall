package com.msb.dongbao.protal.web.filter;

import cn.hutool.json.JSONObject;
import com.msb.dongbao.protal.web.api.CheckUtils;
import com.msb.dongbao.protal.web.util.BodyReaderHttpServletRequestWrapper;
import com.msb.dongbao.protal.web.util.HttpParamUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.SortedMap;

@Component
public class SignAuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter生效了");
        //签名的验证
//        HttpServletRequest request=(HttpServletRequest)servletRequest;
        //下面一不解决了filter中的问题：进入filter之后回不到controller中了
        HttpServletRequest request = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) servletRequest);
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        //获取参数，统一get和post，不管url，还是 body
        /*HttpParamUtils.getUrlParam(request);
        HttpParamUtils.getBodyParam(request);*/
        SortedMap<String, String> allParam = HttpParamUtils.getAllParam(request, response);
        //校验签名
        boolean b = CheckUtils.checkSign(allParam);
        System.out.println("校验签名的结果是："+b);
        if (b){
            filterChain.doFilter(request,response);
        }else {
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            PrintWriter writer = response.getWriter();
            com.alibaba.fastjson.JSONObject param = new com.alibaba.fastjson.JSONObject();
            //JSONObject param = new JSONObject();
            param.put("code",-1);
            param.put("message","签名出错了");
            writer.append(param.toJSONString());
        }

    }

    @Override
    public void destroy() {

    }
}
