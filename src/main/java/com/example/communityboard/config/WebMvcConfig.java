package com.example.communityboard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /css/** というURLが来たら、static/css/ フォルダを見に行くように明示的に指定
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");

        // 文字化け対策として、エンコーディングを指定
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
}