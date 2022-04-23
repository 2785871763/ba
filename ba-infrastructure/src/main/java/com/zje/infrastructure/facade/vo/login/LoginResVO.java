package com.zje.infrastructure.facade.vo.login;

import com.zje.auth.facade.vo.BaseResVO;
import lombok.Data;

@Data
public class LoginResVO extends BaseResVO {

    private String account;
    private String userName;
}
