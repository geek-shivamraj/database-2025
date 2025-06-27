package com.geek.mongo.repos;

import com.geek.mongo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserQueryRepository extends MongoRepository<User, String> {

    //The placeholder ?0 references the first parameter of the method.
    @Query("{ 'name' : ?0 }")
    List<User> fetchUsersByName(String name);

    @Query("{ 'name' : { $regex: ?0 } }")
    List<User> fetchUsersByRegexpName(String regexp);

    //the method has 2 parameters, we’re referencing each of these by index in the raw query, ?0 and ?1
    @Query("{ 'age' : { $gt: ?0, $lt: ?1 } }")
    List<User> fetchUsersByAgeBetween(int ageGT, int ageLT);
}
