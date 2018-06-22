package com.urun.counttool.config.bean;

import com.urun.counttool.SpringJUnit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Lei
 * @version 1.0
 */
public class ShiroFilterFactoryBeanConfigTest extends SpringJUnit {

    @Autowired
    private ShiroFilterFactoryBeanConfig shiroFilterFactoryBeanConfig;

    @Test
    public void test() {
        System.out.println(shiroFilterFactoryBeanConfig);

        Map<String, String> filterChainDefinitionMap = shiroFilterFactoryBeanConfig.getFilterChainDefinitionMap();
        filterChainDefinitionMap.forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });
    }

}