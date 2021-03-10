package com.zxtcw.zxtcwsystem.setting.druid;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * @comment 在system module 配置一次数据源
 * 命名unimax其他模块通过spring 工厂进行调用
 * @author Walter(翟笑天)
 * @date 2021/3/5
 */
@Configuration
public class DruidConfig {

    private static final Logger logger = LoggerFactory.getLogger(DruidConfig.class);


    @Bean
    @Primary
    public ServletRegistrationBean druidServlet() {
        logger.info("初始化 Druid Servlet 配置完成 ");
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // IP白名单 (没有配置或者为空，则允许所有访问)
        servletRegistrationBean.addInitParameter("allow", "");
        // IP黑名单(共同存在时，deny优先于allow)
        //servletRegistrationBean.addInitParameter("deny", "192.168.1.100");
        //控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "admin");
        //是否能够重置数据 禁用HTML页面上的“Reset All”功能
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    @Primary
    public FilterRegistrationBean filterRegistrationBean() {
        logger.info("初始化 Druid 过滤器配置完成 ");
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }


    @Bean(name="unimax")
    @ConfigurationProperties(prefix = "spring.datasource.unimax")
    @Primary
    public DataSource druidUnimaxDataSource(Environment environment) {
        logger.info("第一数据源初始化完成");
        return DruidDataSourceBuilder.create().build();
    }

    // @Bean(name="unimax")
    // @ConfigurationProperties(prefix = "spring.datasource.unimax")
    // public DataSource getDataSource() {
    //     return new DruidDataSource();
    // }

}