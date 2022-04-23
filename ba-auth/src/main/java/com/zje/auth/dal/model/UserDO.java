package com.zje.auth.dal.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("ba_sys_user")
public class UserDO {

    private String id;
    // 头像
    private String avatar;
    // 性别 0 女 1 男
    private Integer sex;
    // 手机号
    private String mobile;
    // 邮箱
    private String email;
    // 个性签名
    private String sign;
    // 账号
    private String account;
    // 密码
    private String password;
    // 用户名
    private String userName;
    // 最后登录时间
    private Date lastLoginTime;
    // 个性化设置,主数据维护
    private String individuation;
    // 删除标记
    private Integer enableFlag;
    // 创建时间
    private Date createDate;
    // 创建人
    private String createBy;
    // 修改时间
    private Date updateDate;
    // 修改人
    private String updateBy;

}
