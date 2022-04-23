package com.dcy.common.exception;

/**
 * @Author：dcy
 * @Description: 校验异常
 * @Date: 2019/10/14 13:42
 */
public class ValidException extends RuntimeException {

    public ValidException() {
        super();
    }

    public ValidException(String message) {
        super(message);
    }
}
