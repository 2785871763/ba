package com.dcy.common.dto.msg.elem;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author：dcy
 * @Description: 语音消息
 * @Date: 2020/8/3 9:35
 */
@Getter
@Setter
public class SoundElem implements Serializable {
    /**
     * 语音下载地址，可通过该 URL 地址直接下载相应语音
     */
    private String url;
    /**
     * 语音数据大小，单位：字节
     */
    private Long size;
    /**
     * 语音时长，单位：秒
     */
    private Long second;
    /**
     * 语音下载方式标记。目前 Download_Flag 取值只能为2，表示可通过Url字段值的 URL 地址直接下载语音
     */
    private Integer downloadFlag;

}
