package com.dcy.common.dto.msg.elem;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @Author：dcy
 * @Description: 图像消息元素
 * @Date: 2020/8/3 9:37
 */
@Getter
@Setter
public class ImageElem implements Serializable {
    /**
     * 图片序列号。后台用于索引图片的键值
     */
    private String uuid;
    /**
     * 图片格式。JPG = 1，GIF = 2，PNG = 3，BMP = 4，其他 = 255
     */
    private String imageFormat;
    /**
     * 原图、缩略图或者大图下载信息
     */
    private List<ImageInfoObj> imageInfoArray;

    @Getter
    @Setter
    public static class ImageInfoObj implements Serializable {
        /**
         * 图片类型： 1-原图，2-大图，3-缩略图
         */
        private String type;
        /**
         * 图片数据大小，单位：字节
         */
        private Long size;
        /**
         * 图片宽度
         */
        private Integer width;
        /**
         * 图片高度
         */
        private Integer height;
        /**
         * 图片下载地址
         */
        private String url;
    }
}
