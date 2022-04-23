package com.zje.infrastructure.service.service.login.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zje.auth.dal.model.UserDO;
import com.zje.auth.dal.mapper.UserMapper;
import com.zje.auth.service.bo.login.QueryUserBO;
import com.zje.auth.service.service.login.QueryUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryUserServiceImpl implements QueryUserService {

    @Autowired
    private UserMapper userReadMapper;


    @Override
    public QueryUserBO QueryUserInfoByUsername(String account) {
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",account);
        UserDO userDO = userReadMapper.selectOne(queryWrapper);
        QueryUserBO queryUserBO = new QueryUserBO();
        BeanUtils.copyProperties(userDO,queryUserBO);
        return queryUserBO;
    }

}
