package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.service.LoginService;
import org.example.vo.Result;
import org.example.vo.params.LoginParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/***
 * @Description: "login controller"
 * @Author: ZBZ
 * @Date: 2021/8/6
 */

@RestController
@RequestMapping("login")
@Api("登陆模块")
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping
    @ApiOperation("登陆功能")
    public Result login(@RequestBody LoginParam loginParam) {
        return loginService.login(loginParam);
    }



}
