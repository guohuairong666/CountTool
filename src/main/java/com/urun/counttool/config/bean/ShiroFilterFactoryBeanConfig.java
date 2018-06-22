package com.urun.counttool.config.bean;

import com.urun.counttool.support.spring.YamlPropertyLoaderFactory;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Lei
 * @version 1.0
 */
@Component
@PropertySource(name = "shiro.yml", value = "classpath:shiro.yml", factory = YamlPropertyLoaderFactory.class)
@ConfigurationProperties(prefix = "shiro-filter-factory-bean")
public class ShiroFilterFactoryBeanConfig {
    private String loginUrl;
    private String unauthorizedUrl;
    private Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>(16);
    private List<String> filterChainDefinitionList;

    public ShiroFilterFactoryBeanConfig() {
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getUnauthorizedUrl() {
        return unauthorizedUrl;
    }

    public void setUnauthorizedUrl(String unauthorizedUrl) {
        this.unauthorizedUrl = unauthorizedUrl;
    }

    public Map<String, String> getFilterChainDefinitionMap() {
        return filterChainDefinitionMap;
    }

    public void setFilterChainDefinitionMap(Map<String, String> filterChainDefinitionMap) {
        this.filterChainDefinitionMap = filterChainDefinitionMap;
    }

    public void setFilterChainDefinitionList(List<String> filterChainDefinitionList) {
        this.filterChainDefinitionList = filterChainDefinitionList;
        for (String definition : filterChainDefinitionList) {
            int indexOf = definition.indexOf("=");
            if (indexOf == -1) {
                continue;
            }
            String key = definition.substring(0, indexOf);
            String value = definition.substring(indexOf + 1);
            filterChainDefinitionMap.put(key, value);
        }
    }

    @Override
    public String toString() {
        return "ShiroFilterFactoryBeanConfig{" +
                "loginUrl='" + loginUrl + '\'' +
                ", unauthorizedUrl='" + unauthorizedUrl + '\'' +
                ", filterChainDefinitionMap=" + filterChainDefinitionMap +
                ", filterChainDefinitionList=" + filterChainDefinitionList +
                '}';
    }
}
