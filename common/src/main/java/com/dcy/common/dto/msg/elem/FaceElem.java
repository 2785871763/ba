package com.dcy.common.dto.msg.elem;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author：dcy
 * @Description: 表情消息
 * @Date: 2020/8/3 9:33
 */
@Getter
@Setter
public class FaceElem implements Serializable {
    /**
     * 表情索引，用户自定义
     */
    private String index;
    /**
     * 额外数据
     */
    private String data;

}
