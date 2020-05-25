package com.sunwul.eurekaclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
 * @EnableSwagger2  启用Swagger
 */
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
                .title("Eureke-Client")
                /**描述*/
                .description("eureke-client服务测试模块")
                /**创建人*/
                .contact(new Contact("sunwul","","1404512053@qq.com"))
                /**版本*/
                .version("1.0")
                .build();
    }
}
