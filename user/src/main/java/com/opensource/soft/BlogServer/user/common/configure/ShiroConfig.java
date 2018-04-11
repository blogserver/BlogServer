package com.opensource.soft.BlogServer.user.common.configure;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.opensource.soft.BlogServer.user.common.shiro.HttpAjaxAuthcFilter;
import com.opensource.soft.BlogServer.user.common.shiro.SimpleShiroFilter;
import com.opensource.soft.BlogServer.user.common.shiro.credentials.RetryLimitHashedCredentialsMatcher;
import com.opensource.soft.BlogServer.user.common.shiro.realm.UserRealm;

@Configuration
public class ShiroConfig {
	
    @Bean  
    public EhCacheManager cacheManager() {  
    	EhCacheManager ehCacheManager = new EhCacheManager();
    	ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return ehCacheManager;  
    }
    
	@Bean  
    public HashedCredentialsMatcher credentialsMatcher() {  
		RetryLimitHashedCredentialsMatcher hashedCredentialsMatcher = new RetryLimitHashedCredentialsMatcher(cacheManager());  
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;  
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;  
    }
	
	  
    @Bean  
    public UserRealm userRealm() {  
    	UserRealm userRealm = new UserRealm();  
    	userRealm.setCredentialsMatcher(credentialsMatcher());
    	userRealm.setCachingEnabled(true);
    	userRealm.setAuthenticationCachingEnabled(true);
    	userRealm.setAuthenticationCacheName("authenticationCache");
    	userRealm.setAuthorizationCachingEnabled(true);
    	userRealm.setAuthorizationCacheName("authorizationCache");
        return userRealm;  
    }
    
    @Bean
    public DefaultWebSecurityManager securityManager() {  
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();  
        securityManager.setRealm(userRealm());  
        securityManager.setCacheManager(cacheManager());  
        return securityManager;  
    }  
    
    @Bean
    public SimpleShiroFilter simpleShiroFilter() {
    	return new SimpleShiroFilter();
    }
  
    @Bean  
    public ShiroFilterFactoryBean shirFilter() {  
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();  
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        shiroFilterFactoryBean.setLoginUrl("/user/login");
        shiroFilterFactoryBean.setSuccessUrl("/user/");
        shiroFilterFactoryBean.setUnauthorizedUrl("/user/unauth");

        Map<String, Filter> filters = new HashMap<String, Filter>();
        filters.put("SimpleShiroFilter", simpleShiroFilter());
        filters.put("HttpAjaxAuthcFilter", new HttpAjaxAuthcFilter());
		shiroFilterFactoryBean.setFilters(filters);
        
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //注意过滤器配置顺序 不能颠倒  
        filterChainDefinitionMap.put("/resources/**", "anon");

        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/user/register", "anon");
        filterChainDefinitionMap.put("/user/addUser", "anon");
        filterChainDefinitionMap.put("/user/validateCode", "anon");
     //   filterChainDefinitionMap.put("/**", "anon");
        filterChainDefinitionMap.put("/blog/likeBlog", "HttpAjaxAuthcFilter");
        filterChainDefinitionMap.put("/**", "authc");  
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);  
        return shiroFilterFactoryBean;  
    }  
    
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){  
        return new LifecycleBeanPostProcessor();  
    }  
}
