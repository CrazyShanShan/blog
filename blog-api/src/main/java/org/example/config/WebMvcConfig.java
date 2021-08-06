package org.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 跨域配置
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080");
    }
}
