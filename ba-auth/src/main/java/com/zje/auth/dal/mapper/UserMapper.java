package com.zje.auth.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zje.auth.dal.model.UserDO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<UserDO> {
}
