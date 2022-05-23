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
    public User checkCartUser(User user,User user1){
        user1.setName(user.getName());
        user1.setPhone(user.getPhone());
        user1.setSend(user.getSend());

        return userRepository.save(user);
    }
    @Override
    public void saveUser(User user){
        userRepository.save(user);
    }

    @Override
    public User findByUsernameAndPassword(String username,String password){
        return userRepository.findByUsernameAndPassword(username,password);
    }


}
