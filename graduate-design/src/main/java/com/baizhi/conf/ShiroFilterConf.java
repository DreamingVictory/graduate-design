package com.baizhi.conf;

import com.baizhi.Shiro.MyRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroFilterConf {
    @Bean//Shiro过滤器  判断请求是否认证成功                  该方法由工厂调用，该形参存在在工厂中，所以可直接注入类型即可
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //过滤器拦截请求  默认不拦截，所以需要进行相关拦截配置
        Map<String, String> map = new HashMap<String, String>();

        /*注意：顺序是有要求的，对除了首页请求之外的所有请求不拦截*/
        map.put("/**", "anon");
        map.put("/html/index.jsp", "authc");//添加认证过滤器 拦截所有的请求  没有认证则跳转到登录界面
        shiroFilterFactoryBean.setLoginUrl("/html/login.jsp");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);//定义过滤器链（多个过滤器）


        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager getSecurityManager(Realm myRealm, CacheManager cacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setCacheManager(cacheManager);
        securityManager.setRealm(myRealm);
        return securityManager;
    }

    @Bean
    public Realm getRealm(CredentialsMatcher hashedCredentialsMatcher) {
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return myRealm;
    }

    @Bean
    public CredentialsMatcher getCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }

    @Bean
    public CacheManager getCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        return ehCacheManager;
    }

}
