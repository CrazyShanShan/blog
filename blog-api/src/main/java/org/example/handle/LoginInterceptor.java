package org.example.handle;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.dao.pojo.SysUser;
import org.example.service.TokenService;
import org.example.utils.HttpContextUtils;
import org.example.utils.IpUtils;
import org.example.utils.UserThreadLocal;
import org.example.vo.ErrorCode;
import org.example.vo.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * @Description:
 * @Author: ZBZ
 * @Date: 2021/8/7
 */

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {


    @Resource
    private TokenService tokenService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 1. 判断请求的接口是否为 HandlerMethod
         */

        if (!(handler instanceof HandlerMethod)) {
            // 也可能为 RequestResourceHandler
            return true;
        }

        String token = request.getHeader("Authorization");

        log.info("================ request start ================");
        String requestURI = request.getRequestURI();
        log.info("request uri: {}", requestURI);
        log.info("request method: method", request.getMethod());
        log.info("token {}", token);
        log.info("================ request end ================");

        if (StringUtils.isBlank(token)) {
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), "未登陆");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        SysUser sysUser = tokenService.checkToken(token);
        if (sysUser == null) {
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), "未登陆");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        UserThreadLocal.put(sysUser);

        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocal.remove();
    }
}
