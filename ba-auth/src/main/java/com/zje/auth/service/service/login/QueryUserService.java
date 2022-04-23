package com.zje.auth.service.service.login;

import com.zje.auth.service.bo.login.QueryUserBO;

public interface QueryUserService {


    QueryUserBO QueryUserInfoByUsername(String account);

}
