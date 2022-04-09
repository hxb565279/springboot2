package com.hxb.springboot2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

@Configuration
public class MyConfig {

    //创建注册一个国际化器
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
