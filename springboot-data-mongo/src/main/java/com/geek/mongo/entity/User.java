package com.geek.mongo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

@SuperBuilder
@Data
@NoArgsConstructor
public class User {
    @Id
    private String personId;
    private String name;
    private Integer age;
}
