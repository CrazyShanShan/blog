package org.example.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.example.dao.mapper.SysUserMapper;
import org.example.dao.pojo.SysUser;
import org.example.service.LoginService;
import org.example.service.SysUserService;
import org.example.utils.JwtUtils;
import org.example.vo.ErrorCode;
import org.example.vo.Result;
import org.example.vo.params.LoginParam;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/***
 * @Description: "login service impl"
 * @Author: ZBZ
 * @Date: 2021/8/6
 */

@Service
public class LoginServiceImpl implements LoginService {


    @Resource
    private SysUserService sysUserService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;


    private static final String slat = "mszlu!@#";

    @Override
    public Result login(LoginParam loginParam) {
        /**
         * 1. 检查参数是否合法
         * 2. 使用加密盐，对密码进行MD5加密
         * 3. 根据用户名和密码去user表中查询该用户是否存在
         * 4. 如果不存在，登陆失败
         * 5. 如果存在，使用jwt生成token，返回给前端
         * 6. token放入Redis当中， 生成redis token:user信息，设置过期时间
         */

        String account = loginParam.getAccount();
        String password = loginParam.getPassword();

        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }

        // md5加密
        password = DigestUtils.md5Hex(password + slat);
        System.out.println(password);
        SysUser sysUser = sysUserService.findUserByAccountPassword(account, password);

        if (sysUser == null) {
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }

        String token = JwtUtils.createToken(sysUser.getId());

        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(sysUser), 1, TimeUnit.DAYS);
        return Result.success(token);
    }
}
