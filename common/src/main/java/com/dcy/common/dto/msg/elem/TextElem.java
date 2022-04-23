package com.dcy.common.dto.msg.elem;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author：dcy
 * @Description: 文本消息
 * @Date: 2020/8/3 9:25
 */
@Getter
@Setter
public class TextElem implements Serializable {
    /**
     * 消息内容
     */
    private String text;
}
