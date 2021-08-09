package org.example.common.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.utils.HttpContextUtils;
import org.example.utils.IpUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/***
 * @Description: "log aspect"
 * @Author: ZBZ
 * @Date: 2021/8/7
 */

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(org.example.common.aop.LogAnnotation)")
    public void pt(){}


    /**
     *
     * @Author: ZBZ
     * @Date: 2021/8/7
     * @param joinPoint:
     * @return: java.lang.Object
     **/
    @Around("pt()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = joinPoint.proceed();

        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        recordLog(joinPoint, time);
        return result;
    }




    private void recordLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);

        log.info("================ log start ================");
        log.info("module:{}", logAnnotation.module());
        log.info("operation:{}", logAnnotation.operator());

        // 请求类名和方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.info("request method:{}", className +"." +methodName + "()");

        // 请求的参数 和方法名
        Object[] args = joinPoint.getArgs();
        String params;
        params = args.length > 0 ? JSON.toJSONString(args[0]) : "";
        log.info("params:{}", params);

        // 获取Request 设置IP地址
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        log.info("ip{}", IpUtils.getIpAddr(request));

        log.info("execute time: {} ms" + time);
        log.info("================ log end ================");


    }

}
