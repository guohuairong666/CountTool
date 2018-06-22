package com.urun.counttool.config;

import com.urun.counttool.config.bean.ShiroFilterFactoryBeanConfig;
import com.urun.counttool.model.entity.User;
import com.urun.counttool.support.shiro.realm.UserRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lei
 * @version 1.0
 */
@Configuration
public class ShrioConfiguration {


    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


    @Bean("userRealm")
    public UserRealm userRealm() {
        return new UserRealm();
    }

    @Bean("securityManager")
    public WebSecurityManager securityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") WebSecurityManager securityManager,
                                                         @Qualifier("shiroFilterFactoryBeanConfig") ShiroFilterFactoryBeanConfig config) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl(config.getLoginUrl());
        shiroFilterFactoryBean.setUnauthorizedUrl(config.getUnauthorizedUrl());
        shiroFilterFactoryBean.setFilterChainDefinitionMap(config.getFilterChainDefinitionMap());

        return shiroFilterFactoryBean;
    }

}
