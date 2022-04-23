package com.zje.auth.service.api.impl;

import com.zje.auth.facade.api.UserManagerFacade;
import com.zje.auth.facade.vo.login.LoginResVO;
import com.zje.auth.service.bo.login.QueryUserBO;
import com.zje.auth.service.service.login.QueryUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zje
 * @createDate 2021-12-13
 * @desc 用户相关接口
 */
@RestController
public class PasswordLoginFacadeImpl implements UserManagerFacade {

    @Autowired
    private QueryUserService queryUserServicel;

    @Value("${config.info}")
    private String aaa;

    @Override
    public LoginResVO queryUserByUserName(String account) {
        QueryUserBO queryUserBO = queryUserServicel.QueryUserInfoByUsername(account);
        LoginResVO loginResVO = new LoginResVO();
        BeanUtils.copyProperties(queryUserBO,loginResVO);
        return loginResVO;
    }

}
