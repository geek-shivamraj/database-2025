package com.geek.mongo.crud.read.generated.query.methods;

import com.geek.mongo.entity.User;
import com.geek.mongo.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;
import java.util.UUID;

/**
 *  the more common type of query that Spring Data usually provides, auto-generated queries out of method names.
 */
@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.geek.mongo.repos")
public class GeneratedQueryMethods implements CommandLineRunner {

    @Autowired
    private UserRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(GeneratedQueryMethods.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        insertUserData();
        generateQuery();
    }

    private void generateQuery() {

        // FindByX
        System.out.println("Find By X Queries");
        List<User> users1 = repository.findByName("Smith");
        users1.forEach(System.out::println);

        // StartingWith & EndingWith - Case Sensitive
        System.out.println("Starting & Ending with Queries [Case-Sensitive]");
        List<User> users2 = repository.findByNameStartingWith("A");
        users2.forEach(System.out::println);

        List<User> users3 = repository.findByNameEndingWith("h");
        users3.forEach(System.out::println);

        // Between
        System.out.println("Between Queries");
        List<User> users4 = repository.findByAgeBetween(10, 35);
        users4.forEach(System.out::println);

        // Like & OrderBy
        System.out.println("Like & Order By");
        List<User> users5 = repository.findByNameLikeOrderByAgeAsc("A");
        users5.forEach(System.out::println);
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
