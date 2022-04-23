package com.dcy.common.utils;

import java.util.UUID;

/**
 * @Author：ly
 * @Description:
 * @Date: 2020/7/3 0003 下午 4:26
 */
public class UuidUtil {

    public static String generateUuid() {
        //32位的
        String uuid = UUID.randomUUID().toString();
        String[] strArray = uuid.split("\\s*[-]\\s*");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strArray.length; i++) {
            sb.append(strArray[i]);
        }
        uuid = sb.toString();
        return uuid;
    }
}
