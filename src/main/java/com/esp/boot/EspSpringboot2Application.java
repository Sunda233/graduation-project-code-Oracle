package com.esp.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@MapperScan("com.esp.boot.mapper")/*扫描mapper，可以不用标注mapper注解*/
@ServletComponentScan(basePackages = "com.esp.boot")/*自动将servlet扫描进来servlet*/
@SpringBootApplication
public class EspSpringboot2Application {

    public static void main(String[] args) {
        SpringApplication.run(EspSpringboot2Application.class, args);
    }

}
