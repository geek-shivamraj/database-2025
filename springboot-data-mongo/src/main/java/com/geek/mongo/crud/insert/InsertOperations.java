package com.geek.mongo.crud.insert;

import com.geek.mongo.entity.Person;
import com.geek.mongo.repos.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;
import java.util.UUID;

/**
 * 	No need to explicitly add @EnableMongoRepositories(basePackages = "com.geek.mongo.repos")
 * 	as this is on same level as repo package
 */
@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.geek.mongo.repos")
public class InsertOperations implements CommandLineRunner {

    @Autowired
    private PersonRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(InsertOperations.class, args);
    }

    @Override
    public void run(String... args) {

        System.out.println("********** Insert Operation *********");
        // Clear the collection
        repository.deleteAll();

        Person person1 = Person.builder().personId(UUID.randomUUID().toString()).firstName("save").lastName("only").build();
        repository.save(person1);

        List<Person> persons = List.of(
                Person.builder().personId(UUID.randomUUID().toString()).firstName("save").lastName("all").build(),
                Person.builder().personId(UUID.randomUUID().toString()).firstName("Alice").lastName("Smith").build(),
                Person.builder().personId(UUID.randomUUID().toString()).firstName("Will").lastName("Smith").build(),
                Person.builder().personId(UUID.randomUUID().toString()).firstName("Jack").lastName("Smith").build(),
                Person.builder().personId(UUID.randomUUID().toString()).firstName("Goku").lastName("Son").build(),
                Person.builder().personId(UUID.randomUUID().toString()).firstName("Gohan").lastName("Son").build()
        );
        repository.saveAll(persons);
    }
}
