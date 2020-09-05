package com.downing.boot.admin.config;

import com.downing.boot.common.DowningResult;
import com.downing.boot.entity.SysUser;
import com.downing.boot.utils.JsonUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理登录验证失败的类
 *
 * @author downing
 * @date 2020年9月4日17:46:51
 */
@Component
public class AuthFailHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        DowningResult result = new DowningResult();
        result.setCode(401);
        result.setMessage("账号或密码错误");
        result.setData("账号或密码错误");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JsonUtils.toJson(result));
    }
}