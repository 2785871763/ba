package com.dcy.common.dto.msg;

/**
 * 消息类型 枚举类
 */
public enum MsgType {
    // 文本消息
    TEXT,
    // 地理位置
    LOCATION,
    // 表情消息
    FACE,
    // 语音
    SOUND,
    // 图片
    IMAGE,
    // 文件
    FILE,
    // 视频
    VIDEO,

    // 下面后续扩展类型，暂时支持自定义格式
    // 系统消息
    SERVER
}
