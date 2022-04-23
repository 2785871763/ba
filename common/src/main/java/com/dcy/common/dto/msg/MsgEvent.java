package com.dcy.common.dto.msg;

/**
 * 消息事件 枚举类
 */
public enum MsgEvent {
    // 单聊
    P2P,
    // 群聊
    GROUP,
    // ping
    PING,
    // pong
    PONG,
    // 系统消息
    SERVER
}
