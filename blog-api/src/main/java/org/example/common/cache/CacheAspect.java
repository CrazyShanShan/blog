package org.example.common.cache;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Arg;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.example.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.time.Duration;

/***
 * @Description: "cache aspect"
 * @Author: ZBZ
 * @Date: 2021/8/12
 */

@Aspect
@Component
@Slf4j
public class CacheAspect {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Pointcut("@annotation(org.example.common.cache.Cache)")
    public void pt() {
    }


    @Around("pt()")
    public Object around(ProceedingJoinPoint pjp) {
        try {
            Signature signature = pjp.getSignature();
            // className
            String className = pjp.getTarget().getClass().getSimpleName();
            // 调用的方法
            String methodName = signature.getName();

            Class[] parameterTypes = new Class[pjp.getArgs().length];
            Object[] args = pjp.getArgs();

            String params = "";
            for (int i = 0; i < args.length; i++) {
                if (args[i] != null) {
                    params += args[i].getClass();
                    parameterTypes[i] = args[i].getClass();
                } else {
                    parameterTypes[i] = null;
                }
            }
            log.info("params: {}", params);

            if (StringUtils.isNotEmpty(params)) {
                // 加密，以放置出现key过长以及字符串转义获取不到的情况
                params = DigestUtils.md5Hex(params);
            }

            Method method = signature.getDeclaringType().getMethod(methodName, parameterTypes);

            // 获取Cache注释
            Cache annotation = method.getAnnotation(Cache.class);
            // 缓存过期时间
            long expire = annotation.expire();
            // 缓存名称
            String name = annotation.name();
            ;
            // key
            String redisKey = name + "::" + className + "::" + methodName + "::" + params;
            String redisValue = redisTemplate.opsForValue().get(redisKey);
            if (StringUtils.isNotEmpty(redisValue)) {
                log.info("走的缓存: {},{}", className, methodName);
                Result result = JSON.parseObject(redisValue, Result.class);
                return result;
            }

            Object proceed = pjp.proceed();

            // 存入缓存
            redisTemplate.opsForValue().set(redisKey, JSON.toJSONString(proceed), Duration.ofMillis(expire));
            log.info("存入缓存～～:{},{}", className, methodName);
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return Result.fail(-999, "系统错误");
    }


}
