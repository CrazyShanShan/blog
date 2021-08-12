package org.example.common.cache;


import java.lang.annotation.*;

/**
 * Cacche
 * @Author: ZBZ
 * @Date: 2021/8/12
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {

    long expire() default 1*60*1000;

    // 缓存标识 key
    String name() default "";


}
