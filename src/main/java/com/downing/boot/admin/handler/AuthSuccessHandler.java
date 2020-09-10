package com.downing.boot.admin.handler;

import com.downing.boot.common.DowningResult;
import com.downing.boot.entity.SysUser;
import com.downing.boot.utils.JsonUtils;
import com.downing.boot.utils.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 处理登录验证成功的类
 *
 * @author downing
 * @date 2020年9月4日17:47:04
 */
@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        //UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("管理员 " + authentication.getName() + " 登录");
        DowningResult result = new DowningResult();
        result.setCode(200);
        result.setMessage("登陆成功");
        //生成token返回
        String token = jwtTokenUtil.generateToken(authentication.getName());
        result.setData(token);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JsonUtils.toJson(result));
    }

}