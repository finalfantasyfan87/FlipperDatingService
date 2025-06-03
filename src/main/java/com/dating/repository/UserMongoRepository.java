package com.dating.repository;

import com.dating.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface UserMongoRepository extends MongoRepository<User, String> {

    List<User> findAllByZodiacSign(String zodiacSign);
   List<String> findInterestsById(String id);
   User findUserByUserName(String userName);

}
