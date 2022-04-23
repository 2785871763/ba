package com.dcy.common.constant;

/**
 * @Author：dcy
 * @Description: 全局公共常量
 * @Date: 2019/9/6 13:36
 */
public interface CommonConstant {
    /**
     * token请求头名称
     */
    String TOKEN_HEADER = "Authorization";

    /**
     * The access token issued by the authorization server. This value is REQUIRED.
     */
    String ACCESS_TOKEN = "access_token";

    String BEARER_TYPE = "Bearer ";
    /**
     * ROLE_ANONYMOUS 匿名权限
     */
    String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

    /**
     * 公共父级id
     */
    String DEFAULT_PARENT_VAL = "0";
    /**
     * 删除
     */
    String STATUS_DEL = "1";

    /**
     * 正常
     */
    String STATUS_NORMAL = "0";

    /**
     * 锁定
     */
    String STATUS_LOCK = "9";

    /**
     * 目录
     */
    Integer CATALOG = -1;

    /**
     * 菜单
     */
    Integer MENU = 1;

    /**
     * 权限
     */
    Integer PERMISSION = 2;


    // auth-server 常量
    String SIGNING_KEY = "dcy!#$&*(!FGE";
    String CONTEXT_KEY_USER_ID = "currentUserId";
    String USER_INFO = "user_info";
    String CONTEXT_KEY_USERNAME = "currentUserName";
    String CONTEXT_KEY_URL = "currentURL";

    String CONTEXT_KEY_TENANT_ID = "currentTenantId";


    /**
     * 用户群组
     */
    String USER_LOGIN_GROUP = "userinfo:group:";
    /**
     * 用户在线
     */
    String USER_ONLINE = "userinfo:online";
    /**
     * 全局的组名称
     */
    String ALL_CHANNEL_GROUP_NAME = "server";
}
