package com.dcy.common.dto.msg;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author：dcy
 * @Description: 消息元素
 * @Date: 2020/8/3 9:15
 */
@Getter
@Setter
public class MsgElement implements Serializable {

    /**
     * 消息类型
     */
    private MsgType type = MsgType.TEXT;

    /**
     * 消息元素的内容，根据不同的消息类型，内容也是不一样的
     * 支持组合消息，消息元素数量无限制
     */
    private Object msgContent;
}
