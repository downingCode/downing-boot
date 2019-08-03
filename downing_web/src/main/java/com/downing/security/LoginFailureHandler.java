package com.downing.security;

import com.alibaba.fastjson.JSONObject;
import com.downing.common.DowningResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author downing
 * @descript 登录失败处理
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        DowningResult result = new DowningResult(-401, "账号或密码错误", null);
        response.getWriter().write(JSONObject.toJSONString(result));
    }
}
