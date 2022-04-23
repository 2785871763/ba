package com.dcy.common.exception;

/**
 * @Author：dcy
 * @Description: 业务异常
 * @Date: 2020/3/21 8:20
 */
public class BusinessException extends RuntimeException {

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

}
