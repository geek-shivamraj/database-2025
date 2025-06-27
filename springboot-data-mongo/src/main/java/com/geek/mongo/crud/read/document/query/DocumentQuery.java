package com.geek.mongo.crud.read.document.query;

import com.geek.mongo.entity.ExtendedPerson;
import com.geek.mongo.entity.Person;
import com.geek.mongo.repos.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;
import java.util.UUID;

/**
 * One of the more common ways to query MongoDB with Spring Data is by making use of the Query and Criteria classes, which very closely mirror native operators.
 */
@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.geek.mongo.repos")
public class DocumentQuery implements CommandLineRunner {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PersonRepository repository;

    private static final String RANDOM_UUID = UUID.randomUUID().toString();

    public static void main(String[] args) {
        SpringApplication.run(DocumentQuery.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        insertExtendedPersons();
        documentQuery();
    }

    private void documentQuery() {

        // Single Criteria
        System.out.println("***** Query 1 - firstName ******");
        Query query1 = new Query();
        query1.addCriteria(Criteria.where("firstName").is("Alice"));
        Person person1 = mongoTemplate.findOne(query1, Person.class);
        System.out.println(person1);

        // 1st way of having Multiple Criteria
        System.out.println("***** Query 1 - lastName ******");
        query1.addCriteria(Criteria.where("lastName").is("Smith"));
        person1 = mongoTemplate.findOne(query1, Person.class);
        System.out.println(person1);

        // 2nd way of having Multiple Criteria
        System.out.println("***** Query 2 - firstName & lastName ******");
        Query query2 = new Query();
        query2.addCriteria(Criteria.where("firstName").is("Goku")
                .andOperator(Criteria.where("lastName").is("Son")));
        Person person2 = mongoTemplate.findOne(query2, Person.class);
        System.out.println(person2);

        System.out.println("***** Query 3 - Age > 10 , < 30 ******");
        Query query3 = new Query();
        query3.addCriteria(Criteria.where("age").gte(10).lt(30));
        List<Person> personList = mongoTemplate.find(query3, Person.class);
        personList.forEach(System.out::println);

        System.out.println("***** Query 4 - Regex ******");
        Query query4 = new Query();
        query4.addCriteria(Criteria.where("firstName").regex("^G"));
        List<Person> query4Persons = mongoTemplate.find(query4, Person.class);
        query4Persons.forEach(System.out::println);

        System.out.println("***** Query 5 - Sort ******");
        Query query5 = new Query();
        query5.with(Sort.by(Sort.Direction.ASC, "age"));
        List<Person> sortedPersons = mongoTemplate.find(query5, Person.class);
        sortedPersons.forEach(System.out::println);

        System.out.println("***** Query 6 - Pageable ******");
        Query query6 = new Query();
        query6.with(PageRequest.of(0, 3));
        List<Person> pagedPersons = mongoTemplate.find(query6, Person.class);
        pagedPersons.forEach(System.out::println);
    }

    public void insertExtendedPersons() {
        // Clear the collection
        repository.deleteAll();

        ExtendedPerson.builder().department("abc").build();
        List<ExtendedPerson> persons = List.of(
                ExtendedPerson.builder().personId(RANDOM_UUID).firstName("Jack").lastName("Doe").department("Math").occupation("Student").age(10).build(),
                ExtendedPerson.builder().personId(UUID.randomUUID().toString()).firstName("Alice").lastName("Smith").department("Math")
                        .occupation("Student").age(20).build(),
                ExtendedPerson.builder().personId(UUID.randomUUID().toString()).firstName("Will").lastName("Smith").department("Business")
                        .occupation("Intern").age(35).build(),
                ExtendedPerson.builder().personId(UUID.randomUUID().toString()).firstName("Jack").lastName("Smith").department("Physics")
                        .occupation("Lecturer").age(42).build(),
                ExtendedPerson.builder().personId(UUID.randomUUID().toString()).firstName("Goku").lastName("Son").department("History")
                        .occupation("Intern").age(24).build(),
                ExtendedPerson.builder().personId(UUID.randomUUID().toString()).firstName("Gohan").lastName("Son").department("History")
                        .occupation("Dean").age(42).build()
        );
        repository.saveAll(persons);
    }

}