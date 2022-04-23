package com.zje.auth.facade.vo.login;

import com.zje.auth.facade.vo.BaseReqVO;
import lombok.Data;

@Data
public class LoginReqVO extends BaseReqVO {

    private Long id;
    private String username;

}
