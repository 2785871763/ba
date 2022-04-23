package com.dcy.common.utils;

import cn.hutool.extra.servlet.ServletUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author：dcy
 * @Description: jwt加密工具
 * @Date: 2020/7/22 15:48
 */
public class JwtUtil {

    public static final int DAY = 3; // 3 天
    public static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK sdfkjsdrow32234545fdf>?N<:{LWPW";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "authorization";

    /**
     * 加密工具
     *
     * @param userJson
     * @return
     */
    public static String generateToken(String userJson) {
        HashMap<String, Object> map = new HashMap<>();
        //you can put any data in the map
        map.put("userJson", userJson);
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        now.set(Calendar.DATE, now.get(Calendar.DATE) + DAY);
        String jwt = Jwts.builder()
                .setClaims(map)  //
                .setExpiration(now.getTime())//过期时间
                .signWith(SignatureAlgorithm.HS512, SECRET)//SECRET是加密算法对应的密钥，这里使用额是HS256加密算法
                .compact();
        return jwt;
    }

    /**
     * 验证
     *
     * @param token
     * @return
     */
    public static String validateToken(String token) {
        if (token != null) {
            // parse the token.
            Map<String, Object> body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            String userJson = (String) (body.get("userJson"));
            if (userJson == null || userJson.isEmpty()) {
                throw new TokenValidationException("Wrong token without username");
            } else {
                return userJson;
            }
        } else {
            throw new TokenValidationException("Missing token");
        }
    }

    /**
     * 获取token
     *
     * @param request
     * @return
     */
    public static String getAuthToken(HttpServletRequest request) {
        Cookie cookie = ServletUtil.getCookie(request, JwtUtil.HEADER_STRING);
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

    static class TokenValidationException extends RuntimeException {
        public TokenValidationException(String msg) {
            super(msg);
        }
    }
}
