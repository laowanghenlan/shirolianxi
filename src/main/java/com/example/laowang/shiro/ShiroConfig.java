package com.example.laowang.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/*
*Shiro的配置类
* */
@Configuration
public class ShiroConfig {
    /*
    * 创建ShiroFilterFactoryBean
    * */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加Shiro内置过滤器
        /*
        * Shiro常用的内置过滤器
        *    anon: 无需认证（登录）即可访问
        *    authc: 必须认证才可以访问
        *    perms: 该资源必须得到资源授权才可以访问
        *    role:  该资源必须得到角色授权才可以访问
        *    user: 如果使用remeberMe的功能可以直接访问
        *
        * */
        Map<String, String> filterMap = new LinkedHashMap<String, String>();

        /*filterMap.put("/add","authc");
        filterMap.put("/update","authc");*/



        filterMap.put("/testThymeleaf","anon");
        filterMap.put("/*","authc");


        //修改跳转的认证登录页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }
    /*
    * 创建DefaultWebSecurityManager
    * */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /*
    * 创建Realm
    * */
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }

}
