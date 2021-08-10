package org.example.config;

import org.example.handle.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/***
 * @Description: "MVC Config"
 * @Author: ZBZ
 * @Date: 2021/8/5
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    //
    // @Override
    // public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //     // 资源配置
    //     registry.addResourceHandler("swagger-ui.html")
    //             .addResourceLocations("classpath:/META-INF/resources/");
    //     // registry.addResourceHandler("/webjars/**")
    //     //         .addResourceLocations("classpath:/META-INF/resources/webjars/");
    // }

    @Resource
    private LoginInterceptor loginInterceptor;


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 跨域配置
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/test")
                .addPathPatterns("/comments/create/change");
    }
}
