package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/***
 * @Description: "Swag 配置"
 * @Author: ZBZ
 * @Date: 2021/8/5
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /***
     * 配置映射路径以及扫描
     * @Author: ZBZ
     * @Date: 2021/8/5

     * @return: springfox.documentation.spring.web.plugins.Docket
     **/
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.example.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("Blog系统")
                        .description("Blog系统，API")
                        .version("1.0")
                        // .contact(new Contact("博客系统呀","还没写好","824375721@qq.com"))
                        // .license("The Apache License")
                        // .licenseUrl("http://www.baidu.com")
                        .build());
    }

}
