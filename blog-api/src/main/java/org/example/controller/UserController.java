package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.service.SysUserService;
import org.example.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/***
 * @Description: ""
 * @Author: ZBZ
 * @Date: 2021/8/6
 */
@RestController
@RequestMapping("users")
@Api("user controller")
public class UserController {


    @Resource
    private SysUserService sysUserService;


    /***
     * 获取登陆User
     * @Author: ZBZ
     * @Date: 2021/8/6
     * @param token: token
     * @return: org.example.vo.Result
     **/
    @PostMapping("currentUser")
    @ApiOperation("返回登陆成功的用户信息")
    public Result currentUser(@RequestHeader("Authorization") String token) {
        return sysUserService.findUserByToken(token);
    }

}
