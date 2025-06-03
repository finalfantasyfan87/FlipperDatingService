package com.dating.service;

import com.dating.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
   public Optional<User> getUserById(String id);
   public List<User> getUsersByZodiacSign(String zodiacSign);
   public List<User> getAllUsers();
   public List<String> getInterestsById(String id);
   public User findUserByUserName(String userName);
   public User saveUser(User user);
}
