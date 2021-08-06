package org.example.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.example.dao.pojo.SysUser;
import org.example.service.TokenService;
import org.example.utils.JwtUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/***
 * @Description:
 * @Author: ZBZ
 * @Date: 2021/8/6
 */

@Service
public class TokenServiceImpl implements TokenService {


    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public SysUser checkToken(String token) {

        if (StringUtils.isBlank(token)) {
            return null;
        }
        Map<String, Object> stringObjectMap = JwtUtils.checkToken(token);
        if (stringObjectMap == null) {
            return null;
        }
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if (StringUtils.isBlank(userJson)) {
            return null;
        }
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        return sysUser;
    }
}
