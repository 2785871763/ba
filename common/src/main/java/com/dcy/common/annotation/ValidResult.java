package com.dcy.common.annotation;

import java.lang.annotation.*;

/**
 * @author dcy
 * @Date: 2019/10/14 10:17
 * @Description: 是否校验对象
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidResult {
    String value() default "";
}
