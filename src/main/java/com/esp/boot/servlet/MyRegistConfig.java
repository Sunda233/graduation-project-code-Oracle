package com.esp.boot.servlet;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * 1、MyServlet --> /my
 * 2、DispatcherServlet --> /
 * 写一个配置类，将拦截器过滤器配置到容器中，
 * 这个类相当于原生的webxml（自己理解）sunda
 */
// (proxyBeanMethods = true)：保证依赖的组件始终是单实例的，false每次都要新建一次，会有冗余的对象
@Configuration(proxyBeanMethods = true)
public class MyRegistConfig {

    @Bean
    public ServletRegistrationBean myServlet(){
        MyServlet myServlet = new MyServlet();

        return new ServletRegistrationBean(myServlet,"/my","/my02");/*servlet要处理的请求*/
    }


    @Bean
    public FilterRegistrationBean myFilter(){

        MyFilter myFilter = new MyFilter();
        /*方法一使用上文路径*/
//        return new FilterRegistrationBean(myFilter,myServlet());/*可以直接拦截上面方法的路径*/
        /*方法二自己写路径*/
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(myFilter);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/my","/css/*"));/*写要拦截的路径*/
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean myListener(){
        MySwervletContextListener mySwervletContextListener = new MySwervletContextListener();
        return new ServletListenerRegistrationBean(mySwervletContextListener);
    }
}
