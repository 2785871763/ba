package com.dcy.common.model;

import lombok.Data;

/**
 * @Author：dcy
 * @Description: 日志打印实体类
 * @Date: 2019/10/15 9:06
 */
@Data
public class OperationalLog {


    /**
     * 请求地址
     */
    private String url;

    /**
     * 方法名
     */
    private String method;

    /**
     * 操作人员
     */
    private String operName;

    /**
     * 请求参数
     */
    private String operParam;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 执行时间 获取毫秒数
     */
    private long exeTime;

    /**
     * 返回结果
     */
    private String result;

    /**
     * 错误信息
     */
    private String error;


}
