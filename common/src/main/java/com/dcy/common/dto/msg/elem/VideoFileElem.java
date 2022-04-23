package com.dcy.common.dto.msg.elem;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2020/8/3 9:42
 */
@Getter
@Setter
public class VideoFileElem implements Serializable {
    /**
     * 视频下载地址。可通过该 URL 地址直接下载相应视频。
     */
    private String videoUrl;
    /**
     * 视频数据大小，单位：字节
     */
    private Long videoSize;
    /**
     * 视频时长，单位：秒
     */
    private Long videoSecond;
    /**
     * 视频格式，例如 mp4
     */
    private String videoFormat;
    /**
     * 视频下载方式标记。目前 VideoDownloadFlag 取值只能为2，表示可通过VideoUrl字段值的 URL 地址直接下载视频。
     */
    private Integer videoDownloadFlag;
    /**
     * 视频缩略图下载地址。可通过该 URL 地址直接下载相应视频缩略图
     */
    private String thumbUrl;
    /**
     * 缩略图大小，单位：字节
     */
    private Long thumbSize;
    /**
     * 缩略图宽度
     */
    private Integer thumbWidth;
    /**
     * 缩略图高度
     */
    private Integer thumbHeight;
    /**
     * 缩略图格式，例如 JPG、BMP 等
     */
    private String thumbFormat;
    /**
     * 视频缩略图下载方式标记。目前 ThumbDownloadFlag 取值只能为2，表示可通过ThumbUrl字段值的 URL 地址直接下载视频缩略图
     */
    private Integer thumbDownloadFlag;
}
