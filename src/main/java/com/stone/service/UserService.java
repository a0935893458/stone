package com.stone.service;

import com.stone.domain.User;

public interface UserService {
    User findOne(Long id);

    User checkCartUser(User user,User user1);

    void saveUser(User user);
    User findByUsernameAndPassword(String username,String password);
}
