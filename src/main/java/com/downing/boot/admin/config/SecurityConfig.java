package com.downing.boot.admin.config;

import com.downing.boot.admin.filter.CustomAuthenticationFilter;
import com.downing.boot.admin.handler.AuthAccessDeniedHandler;
import com.downing.boot.admin.handler.AuthFailHandler;
import com.downing.boot.admin.handler.AuthLogoutHandler;
import com.downing.boot.admin.handler.AuthSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author downing
 * @desc 安全配置类
 * @date 2020/9/4 16:31
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthSuccessHandler authSuccessHandler;

    @Autowired
    private AuthFailHandler authFailHandler;

    @Autowired
    private AuthLogoutHandler authLogoutHandler;

    @Autowired
    private AuthAccessDeniedHandler authAccessDeniedHandler;

    @Autowired
    private MyAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //"/login"不进行权限验证
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/login").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated()   //其他的需要登陆后才能访问
                .and()
                .formLogin()
                //loginProcessingUrl用于指定前后端分离的时候调用后台登录接口的名称
                .loginProcessingUrl("/login")
                //配置登录成功的自定义处理类
                .successHandler(authSuccessHandler)
                //配置登录失败的自定义处理类
                .failureHandler(authFailHandler)
                .and()
                //loginProcessingUrl用于指定前后端分离的时候调用后台注销接口的名称
                .logout().logoutUrl("/logout")
                .logoutSuccessHandler(authLogoutHandler)
                .and()
                //配置没有权限的自定义处理类
                .exceptionHandling().accessDeniedHandler(authAccessDeniedHandler)
                .and()
                //将自定义的filter添加到UsernamePasswordAuthenticationFilter位置
                .addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .cors()//新加入
                .and()
                .csrf().disable();// 取消跨站请求伪造防护
    }

    //注册自定义的UsernamePasswordAuthenticationFilter
    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(authSuccessHandler);
        filter.setAuthenticationFailureHandler(authFailHandler);
        filter.setFilterProcessesUrl("/login");
        //重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //启用自己的登陆验证逻辑
        auth.authenticationProvider(authenticationProvider);
    }
}
