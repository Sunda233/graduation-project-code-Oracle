package com.esp.boot.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * 过滤器，拦截器
 */
@Slf4j
//@WebFilter(urlPatterns={"/css/*","/images/*"}) //my，拦截器，拦截掉这些路径
/*单*是Servlet写法，**是spring写法*/
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("MyFilter初始化完成");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("MyFilter工作");
        chain.doFilter(request,response);/*放行*/
    }

    @Override
    public void destroy() {
        log.info("MyFilter销毁");
    }
}
