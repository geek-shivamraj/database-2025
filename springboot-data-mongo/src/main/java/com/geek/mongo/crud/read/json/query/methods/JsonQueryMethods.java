package com.geek.mongo.crud.read.json.query.methods;

import com.geek.mongo.entity.User;
import com.geek.mongo.repos.UserQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;
import java.util.UUID;

/**
 *  If we can’t represent a query with the help of a method name or criteria, we can do something more low level, use the @Query annotation.
 *
 *  With this annotation, we can specify a raw query as a Mongo JSON query string.
 */
@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.geek.mongo.repos")
public class JsonQueryMethods implements CommandLineRunner {

    @Autowired
    private UserQueryRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(JsonQueryMethods.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        insertUserData();
        jsonQuery();
    }

    private void jsonQuery() {

        // FindBy
        System.out.println("Json Query: Find By");
        List<User> users1 = repository.fetchUsersByName("Smith");
        users1.forEach(System.out::println);

        // $regex
        System.out.println("Json Query: $Regex");
        List<User> users2 = repository.fetchUsersByRegexpName("^A");
        users2.forEach(System.out::println);

        // $lt & $gt
        System.out.println("Json Query: $lt & $gt");
        List<User> users3 = repository.fetchUsersByAgeBetween(10, 35);
        users3.forEach(System.out::println);
    }

    private void insertUserData() {
        // Clear the collection
        repository.deleteAll();

        List<User> users = List.of(
                User.builder().personId(UUID.randomUUID().toString()).name("Jack").age(15).build(),
                User.builder().personId(UUID.randomUUID().toString()).name("Alice").age(25).build(),
                User.builder().personId(UUID.randomUUID().toString()).name("Anthony").age(10).build(),
                User.builder().personId(UUID.randomUUID().toString()).name("Smith").age(35).build(),
                User.builder().personId(UUID.randomUUID().toString()).name("Smith").age(45).build(),
                User.builder().personId(UUID.randomUUID().toString()).name("Goku").age(25).build()
        );
        repository.saveAll(users);
    }
}
