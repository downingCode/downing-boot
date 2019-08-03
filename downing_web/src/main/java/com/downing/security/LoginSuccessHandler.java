package com.downing.security;

import com.alibaba.fastjson.JSONObject;
import com.downing.common.DowningResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author downing
 * @descript 登录成功处理
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        DowningResult result = new DowningResult(200, "登录成功", null);
        response.getWriter().write(JSONObject.toJSONString(result));
        //todo 整合JWT
    }
}
