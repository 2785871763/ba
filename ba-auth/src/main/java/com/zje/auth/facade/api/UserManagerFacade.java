package com.zje.auth.facade.api;

import com.zje.auth.facade.vo.login.LoginResVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("auth/user")
public interface UserManagerFacade {

//    @GetMapping
//    LoginResVO queryUserByUserName(String userName);

    @PostMapping("init")
    LoginResVO queryUserByUserName(String account);

}
