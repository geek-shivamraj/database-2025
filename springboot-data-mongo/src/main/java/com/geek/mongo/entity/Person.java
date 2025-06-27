package com.geek.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *  Since we're using @Data, lombok generates a constructor that accepts an object with the properties
 *   for the SuperBuilder class.
 *  We can fix this by adding @NoArgsConstructor
 */
@Data
@SuperBuilder(toBuilder = true)
@Document(collection = "person")
@NoArgsConstructor
public class Person {
    @Id
    private String personId;
    private String firstName;
    private String lastName;
    private Integer age;
}
