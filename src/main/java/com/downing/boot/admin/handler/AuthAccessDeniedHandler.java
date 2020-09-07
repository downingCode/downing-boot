package com.downing.boot.admin.handler;

import com.downing.boot.common.DowningResult;
import com.downing.boot.utils.JsonUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理没有权限的类
 *
 * @author downing
 * @date 2020年9月4日17:47:16
 */
@Component
public class AuthAccessDeniedHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        DowningResult result = new DowningResult();
        result.setCode(403);
        result.setMessage("没有权限");
        result.setData("没有权限");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JsonUtils.toJson(result));
    }
}