package com.zje.infrastructure.service.service.login;

import com.zje.auth.service.bo.login.QueryUserBO;

public interface QueryUserService {


    QueryUserBO QueryUserInfoByUsername(String account);

}
