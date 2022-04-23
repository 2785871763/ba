package com.zje.infrastructure.facade.api;

import com.zje.infrastructure.facade.vo.login.LoginResVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping()
public interface UserManagerFacade {

    @GetMapping
    void test();

}
