package com.dating.service.impl;

import com.dating.service.UserService;
import com.dating.model.User;
import com.dating.repository.UserMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("userService")

public class UserServiceImpl implements UserService {
    final
    UserMongoRepository userMongoRepository;

    public UserServiceImpl(UserMongoRepository userMongoRepository) {
        this.userMongoRepository = userMongoRepository;
    }

    //use MongoTemplate for unique query functions

    @Override
    public Optional<User> getUserById(String id) {
        return userMongoRepository.findById(id);
    }

    @Override
    public List<User> getUsersByZodiacSign(String zodiacSign) {
        return userMongoRepository.findAllByZodiacSign(zodiacSign);
    }

    @Override
    public List<User> getAllUsers() {
        return userMongoRepository.findAll();
    }

    @Override
    public List<String> getInterestsById(String id) {
        return userMongoRepository.findInterestsById(id);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userMongoRepository.findUserByUserName(userName);
    }

    @Override
    public User saveUser(User user) {
        return userMongoRepository.save(user);
    }
}
