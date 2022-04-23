package com.dcy.common.dto.msg.elem;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2020/8/3 9:41
 */
@Getter
@Setter
public class FileElem implements Serializable {
    /**
     * 文件下载地址，可通过该 URL 地址直接下载相应文件
     */
    private String url;
    /**
     * 文件数据大小，单位：字节
     */
    private Long fileSize;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件下载方式标记。目前 Download_Flag 取值只能为2，表示可通过Url字段值的 URL 地址直接下载文件。
     */
    private Integer downloadFlag;
}
