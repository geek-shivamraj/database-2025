package com.geek.mongo.entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@SuperBuilder
@Document(collation = "person")
public class Person {
    @Id
    private UUID personId;
    private String firstName;
    private String lastName;
    private int age;
}
