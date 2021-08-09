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
import org.springframework.beans.BeanUtils;
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

        SysUser sysUser = sysUserService.findUserByAccountPassword(account, password);

        if (sysUser == null) {
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }

        String token = JwtUtils.createToken(sysUser.getId());

        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(sysUser), 1, TimeUnit.DAYS);
        return Result.success(token);
    }

    @Override
    public Result logout(String token) {
        /**
         * 登出，将redis里面的set清楚
         */
        redisTemplate.delete("TOKEN_" + token);
        return Result.success();
    }

    @Override
    public Result register(LoginParam loginParam) {
        /**
         * 1. 判断参数是否合法
         * 2. 判断用户是否存在
         * 3. 不存在，注册用户
         * 4. 使用jwt生成token
         * 5. 存入redis，并返回
         * 6. 加上事物，一旦中间的任何过程出现问题，注册用户失败， 需要回滚
         */
         String account = loginParam.getAccount();
         String password = loginParam.getPassword();
         String nickname = loginParam.getNickname();

         if (StringUtils.isBlank(nickname) || StringUtils.isBlank(password) || StringUtils.isBlank(account)) {
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
         }

         SysUser sysUser = sysUserService.findUserByAccount(account);
         if (sysUser != null) {
             return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(), ErrorCode.ACCOUNT_EXIST.getMsg());
         }

         sysUser = new SysUser();
         BeanUtils.copyProperties(loginParam, sysUser);
         sysUser.setPassword(DigestUtils.md5Hex(password + slat));
         sysUser.setCreateDate(System.currentTimeMillis());
         sysUser.setLastLogin(System.currentTimeMillis());
         sysUser.setAvatar("/static/img/logo.b3a48c0.png");
         sysUser.setAdmin(1);
         sysUser.setDeleted(0);
         sysUser.setSalt("");
         sysUser.setStatus("");
         sysUser.setEmail("");
         sysUserService.save(sysUser);

         String token = JwtUtils.createToken(sysUser.getId());
         redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(sysUser), 1, TimeUnit.DAYS);
         return Result.success(token);
    }
}
