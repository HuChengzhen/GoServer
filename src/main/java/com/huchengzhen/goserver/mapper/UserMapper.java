package com.huchengzhen.goserver.mapper;

import com.huchengzhen.goserver.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findUserById(int id);

    void insert(User user);

    User findUserByUsername(String username);
}
