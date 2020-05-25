package com.sunwul.userinfo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*****
 * @author: Sunwul
 * @date: 2020/1/5 14:30
 * PS: 整合阿里 Druid 数据源
 */
@Configuration
public class DruidConfig {
    /**绑定相关设置*/
    @ConfigurationProperties(prefix="spring.datasource")
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
    }

    /**配置Druid监控
     1.配置一个管理后台的servlet
     访问地址: /druid */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        //配置一个拦截器,并指定拦截器只拦截druid管理页面的请求
        ServletRegistrationBean bean=new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String,String> initParams = new HashMap<>();
        //登录druid管理页面的用户名
        initParams.put("loginUsername","admin");
        //登录druid管理页面的密码
        initParams.put("loginPassword","123");
        //ip白名单，如果没有设置或为空，则表示允许所有访问
        initParams.put("allow","");
        /**initParams.put("deny","192.168.15.21");*/
        bean.setInitParameters(initParams);
        return bean;
    }
    /**2.配置一个监控的filter*/
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*");

        bean.setInitParameters(initParams);

        bean.setUrlPatterns(Arrays.asList("/*"));

        return  bean;
    }
}
