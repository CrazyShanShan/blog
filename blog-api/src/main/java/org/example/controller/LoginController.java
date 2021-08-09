package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.common.aop.LogAnnotation;
import org.example.service.LoginService;
import org.example.vo.Result;
import org.example.vo.params.LoginParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/***
 * @Description: "login controller"
 * @Author: ZBZ
 * @Date: 2021/8/6
 */

@RestController
@Api("登陆模块")
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("login")
    @ApiOperation("登陆功能")
    @LogAnnotation(module = "登陆登出模块", operator = "登陆功能")
    public Result login(@RequestBody LoginParam loginParam) {
        return loginService.login(loginParam);
    }

    @GetMapping("logout")
    @ApiOperation("登出")
    @LogAnnotation(module = "登陆登出模块", operator = "登出功能")
    public Result logout(@RequestHeader("Authorization") String token) {
        return loginService.logout(token);
    }


}
