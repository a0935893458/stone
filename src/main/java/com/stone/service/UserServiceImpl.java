package com.stone.service;

import com.stone.domain.User;
import com.stone.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public User findOne(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User checkCartUser(User user){return userRepository.save(user); }

}
