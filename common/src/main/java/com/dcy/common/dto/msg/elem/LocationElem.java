package com.dcy.common.dto.msg.elem;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author：dcy
 * @Description: 地理位置消息
 * @Date: 2020/8/3 9:26
 */
@Getter
@Setter
public class LocationElem implements Serializable {
    /**
     * 地理位置描述信息
     */
    private String desc;
    /**
     * 纬度
     */
    private Double latitude;
    /**
     * 经度
     */
    private Double longitude;
}
