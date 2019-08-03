package com.downing.security;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author downing
 * @descript 登录拦截器  表单请求
 */
public class LoginSecurityFilter extends AbstractAuthenticationProcessingFilter {

    protected LoginSecurityFilter() {
        //拦截请求路径为login的请求
        super(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            //封装token
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            return this.getAuthenticationManager().authenticate(authenticationToken);
        } else {
            throw new RuntimeException("账号或密码不能为空");
        }
    }
}
