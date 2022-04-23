package com.dcy.common.utils;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 线程帮助类
 */
public class SessionSocketHolder {
    /**
     * 用户名，channel
     */
    private static final Map<String, Channel> CHANNEL_MAP = new ConcurrentHashMap<>(16);

    /**
     * 组id，channelGroup
     */
    private static final Map<String, ChannelGroup> CHANNEL_GROUP_MAP = new ConcurrentHashMap<>(16);


    /**
     * Save the relationship between the userId and the channel.
     *
     * @param userId
     * @param socketChannel
     */
    public static void put(String userId, Channel socketChannel) {
        CHANNEL_MAP.put(userId, socketChannel);
    }

    public static Channel get(String userId) {
        return CHANNEL_MAP.get(userId);
    }

    public static Map<String, Channel> getRelationShip() {
        return CHANNEL_MAP;
    }

    public static void remove(Channel nioSocketChannel) {
        CHANNEL_MAP.entrySet().stream().filter(entry -> entry.getValue() == nioSocketChannel).forEach(entry -> CHANNEL_MAP.remove(entry.getKey()));
    }


    /**
     * 组使用
     *
     * @param groupId
     * @param channelGroup
     */
    public static void put(String groupId, ChannelGroup channelGroup) {
        CHANNEL_GROUP_MAP.put(groupId, channelGroup);
    }

    public static void putChannel(String groupId, Channel channel) {
        CHANNEL_GROUP_MAP.get(groupId).add(channel);
    }

    public static ChannelGroup getGroup(String groupId) {
        return CHANNEL_GROUP_MAP.get(groupId);
    }

    public static void remove(String groupId) {
        CHANNEL_GROUP_MAP.remove(groupId);
    }
}
