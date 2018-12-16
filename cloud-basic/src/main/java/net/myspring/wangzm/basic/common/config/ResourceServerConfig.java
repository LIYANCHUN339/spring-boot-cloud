package net.myspring.wangzm.basic.common.config;

import feign.RequestInterceptor;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Created by on 28.01.16.
 *
 * @author David Steiman
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //设置服务resourceId 如果未设置clientId的resourceIds的值 clientId默认可以访问resourceId为outh2-resource的服务
        resources.resourceId("AuthorizationServer");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
         .anyRequest().authenticated()
                .and().csrf().disable();
    }

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2ClientContext oAuth2ClientContext, AuthorizationCodeResourceDetails authorizationCodeResourceDetails) {
        return new OAuth2FeignRequestInterceptor(oAuth2ClientContext,authorizationCodeResourceDetails);
    }
}
