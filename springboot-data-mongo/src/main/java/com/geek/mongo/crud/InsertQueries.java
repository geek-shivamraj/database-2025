package com.geek.mongo.crud;

import com.geek.mongo.entity.Person;
import com.geek.mongo.repos.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.UUID;

/**
 * 	No need to explicitly add @EnableMongoRepositories(basePackages = "com.geek.mongo.repos")
 * 	as this is on same level as repo package
 */
@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.geek.mongo.repos")
public class InsertQueries  implements CommandLineRunner {

    @Autowired
    private PersonRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(InsertQueries.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Person person1 = Person.builder().personId(UUID.randomUUID()).firstName("shivam").lastName("raj").build();
        repository.save(person1);
    }
}
