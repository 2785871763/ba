package com.zje.infrastructure.service.bo.login;

import com.zje.auth.service.bo.BaseBO;
import lombok.Data;

@Data
public class QueryUserBO extends BaseBO {

    private String account;
    private String password;
    private String userName;

}
