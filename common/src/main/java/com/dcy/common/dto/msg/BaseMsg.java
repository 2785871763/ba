package com.dcy.common.dto.msg;

import cn.hutool.core.date.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2020/7/30 11:01
 */
@Setter
@Getter
public class BaseMsg implements Serializable {

    /**
     * 用户名，发送人
     */
    private String userId;
    /**
     * 创建时间
     */
    private String createTime = DateUtil.formatDateTime(new Date());
    /**
     * 消息事件
     */
    private MsgEvent event = MsgEvent.P2P;

    //=======================后续的内容说明========================
    /**
     * 消息内容
     * 参考地址：https://cloud.tencent.com/document/product/269/2720
     */
    private List<MsgElement> msgBody = new ArrayList<>();
}
