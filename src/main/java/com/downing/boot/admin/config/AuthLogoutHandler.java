package com.downing.boot.admin.config;

import com.downing.boot.common.DowningResult;
import com.downing.boot.utils.JsonUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理退出的类
 *
 * @author downing
 * @date 2020年9月4日17:46:51
 */
@Component
public class AuthLogoutHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        DowningResult result = new DowningResult();
        result.setCode(200);
        result.setMessage("注销成功");
        result.setData("注销成功");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JsonUtils.toJson(result));
    }
}