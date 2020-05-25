package com.sunwul.adminserver.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.boot.autoconfigure.security.servlet.WebSecurityEnablerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/*****
 * @author: Sunwul
 * @date: 2020/2/6 14:49
 * PS: Security配置类，用于配置admin server的安全控制
 */
@Configuration
public class AdminServerSecurityConfig extends WebSecurityEnablerConfiguration {
    private final String adminContextPath;

    /**AdminServerProperties定义了Spring Boot Admin的配置属性*/
    public AdminServerSecurityConfig (AdminServerProperties adminServerProperties) {
        this.adminContextPath=adminServerProperties.getContextPath();
    }

    protected void configure(HttpSecurity http) throws Exception{
        //登录成功处理
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(adminContextPath + "/");

        http.authorizeRequests()
                //静态文件允许访问
                .antMatchers(adminContextPath + "/assets/**").permitAll()
                //登录页面允许访问
                .antMatchers(adminContextPath + "/login").permitAll()
                //其他所有请求需要登录
                .anyRequest().authenticated()
                .and()
                //登录页面配置，用于替换security默认页面
                .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
                //登出页面配置，用于替换security默认页面
                .logout().logoutUrl(adminContextPath + "/logout").and()
                .httpBasic().and()
                //csrf校验,成功登录后一段时间内可以直接访问admin监控页
                .csrf() /**.disable()  禁用*/
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                //不做csrf校验的页面
//                .ignoringAntMatchers(
//                        "/instances",
//                        "/actuator/**"
//                )
                ;
    }
}
