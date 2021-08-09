package org.example.controller;

import org.example.service.LoginService;
import org.example.vo.Result;
import org.example.vo.params.LoginParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/***
 * @Description: "
 * @Author: ZBZ
 * @Date: 2021/8/7
 */

@RestController
@RequestMapping("register")
public class RegisterController {

    @Resource
    private LoginService loginService;

    @PostMapping
    public Result register(@RequestBody LoginParam loginParam) {
        return loginService.register(loginParam);
    }

}
