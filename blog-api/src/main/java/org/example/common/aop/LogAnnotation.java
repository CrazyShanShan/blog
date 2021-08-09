package org.example.common.aop;


import java.lang.annotation.*;

/**
 * 注解
 * @author crazys
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    String module() default "";
    
    String operator() default "";
}
