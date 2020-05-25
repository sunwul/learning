package com.sunwul.zuul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*****
 * @author: Sunwul
 * @date: 2020/1/14 14:20
 * PS: Swagger配置
 * @Configuration 通过configuration注解自动注入配置文件
 * @EnableSwagger2    开启swagger功能
 * @Primary   如果有多个配置文件，以这个为准
 */
@Primary
@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    /**api信息设置,会显示在Swagger-UI界面*/
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                /**页面大标题*/
                .title("Zuul")
                /**描述*/
                .description("Zuul转发中心")
                /**创建人*/
                .contact(new Contact("sunwul","","1404512053@qq.com"))
                /**版本*/
                .version("1.0")
                .build();
    }
}
