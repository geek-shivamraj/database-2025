package com.geek.mongo.crud.read;

import com.geek.mongo.entity.ExtendedPerson;
import com.geek.mongo.entity.Person;
import com.geek.mongo.repos.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 	No need to explicitly add @EnableMongoRepositories(basePackages = "com.geek.mongo.repos")
 * 	as this is on same level as repo package
 */
@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.geek.mongo.repos")
public class ReadOperations implements CommandLineRunner {

    @Autowired
    private PersonRepository repository;

    private static final String RANDOM_UUID = UUID.randomUUID().toString();

    public static void main(String[] args) {
        SpringApplication.run(ReadOperations.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        insertPersons();

        System.out.println("******** Person ************");

        // findByFirstNameAndLastName
        Optional<Person> personByFirstLastName = repository.findByFirstNameAndLastName("Goku", "Son");
        personByFirstLastName.ifPresentOrElse(System.out::println, () -> System.out.println("No match found for Goku & Son"));

        // findByFirstName
        Person person = repository.findByFirstName("Alice");
        System.out.println(person);

        // findByLastName
        List<Person> personList = repository.findByLastName("Smith");
        System.out.println(personList);

        // findAll
        List<Person> allPersonList = repository.findAll();
        System.out.println(allPersonList);

        // findById
        Optional<Person> personById = repository.findById(RANDOM_UUID);
        personById.ifPresent(System.out::println);

        System.out.println("******** Extended Person ************");
        insertExtendedPersons();
        Person extendedPerson = repository.findByFirstName("Alice");
        System.out.println(extendedPerson);

        List<Person> extendedPersonList = repository.findByLastName("Smith");
        System.out.println(extendedPersonList);

        List<Person> allExtendedPersons = repository.findAll();
        System.out.println(allExtendedPersons);

        Optional<Person> extendedPersonById = repository.findById(RANDOM_UUID);
        extendedPersonById.ifPresent(System.out::println);
    }

    public void insertPersons() {
        // Clear the collection
        repository.deleteAll();

        List<Person> persons = List.of(
                Person.builder().personId(RANDOM_UUID).firstName("Jack").lastName("Doe").build(),
                Person.builder().personId(UUID.randomUUID().toString()).firstName("Alice").lastName("Smith").build(),
                Person.builder().personId(UUID.randomUUID().toString()).firstName("Will").lastName("Smith").build(),
                Person.builder().personId(UUID.randomUUID().toString()).firstName("Jack").lastName("Smith").build(),
                Person.builder().personId(UUID.randomUUID().toString()).firstName("Goku").lastName("Son").build(),
                Person.builder().personId(UUID.randomUUID().toString()).firstName("Gohan").lastName("Son").build()
        );
        repository.saveAll(persons);
    }

    public void insertExtendedPersons() {
        // Clear the collection
        repository.deleteAll();

        ExtendedPerson.builder().department("abc").build();
        List<ExtendedPerson> persons = List.of(
                ExtendedPerson.builder().personId(RANDOM_UUID).firstName("Jack").lastName("Doe").department("Math").occupation("Student").build(),
                ExtendedPerson.builder().personId(UUID.randomUUID().toString()).firstName("Alice").lastName("Smith").department("Math").occupation("Student").build(),
                ExtendedPerson.builder().personId(UUID.randomUUID().toString()).firstName("Will").lastName("Smith").department("Business").occupation("Intern").build(),
                ExtendedPerson.builder().personId(UUID.randomUUID().toString()).firstName("Jack").lastName("Smith").department("Physics").occupation("Lecturer").build(),
                ExtendedPerson.builder().personId(UUID.randomUUID().toString()).firstName("Goku").lastName("Son").department("History").occupation("Intern").build(),
                ExtendedPerson.builder().personId(UUID.randomUUID().toString()).firstName("Gohan").lastName("Son").department("History").occupation("Dean").build()
        );
        repository.saveAll(persons);
    }
}