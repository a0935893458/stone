package com.stone.service;

import com.stone.domain.User;

public interface UserService {
    User findOne(Long id);

    User checkCartUser(User user);
}
