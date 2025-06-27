package com.geek.mongo.repos;

import com.geek.mongo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    List<User> findByName(String name);

    List<User> findByNameStartingWith(String regexp);

    List<User> findByNameEndingWith(String regexp);

    List<User> findByAgeBetween(int ageGT, int ageLT);

    List<User> findByNameLikeOrderByAgeAsc(String name);
}
