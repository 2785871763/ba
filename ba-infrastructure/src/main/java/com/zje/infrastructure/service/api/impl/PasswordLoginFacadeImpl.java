package com.zje.infrastructure.service.api.impl;

import com.zje.infrastructure.facade.api.UserManagerFacade;
import com.zje.infrastructure.facade.vo.login.LoginResVO;
import com.zje.infrastructure.service.bo.login.QueryUserBO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zje
 * @createDate 2021-12-13
 * @desc 用户相关接口
 */
@RestController
public class PasswordLoginFacadeImpl implements UserManagerFacade {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public void test() {

    }

}
