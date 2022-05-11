package com.example.store.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PicInterceptorConfigurer implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/avator/**").
                addResourceLocations("file:" + System.getProperty("user.dir") + "/src/main/resources/static/avator/");
        //addResourceLocations("file:d:/MySystem/src/main/resources/static/uploadFile/")

    }
}