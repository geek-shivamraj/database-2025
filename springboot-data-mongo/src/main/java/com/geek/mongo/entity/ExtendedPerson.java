package com.geek.mongo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 *  1. the toBuilder property with @SuperBuilder ensures that each class in the hierarchy can generate a builder that starts
 *     with the values of the current instance, provided that all superclasses also have toBuilder=true
 *
 *  2. If your class does not extend any other class, the warning might still appear, but it is safe to ignore it or explicitly set callSuper to false.
 *     However, if your class does extend another class and you want to include the superclass's fields in the equals and hashCode methods,
 *     you should set callSuper to true.
 *
 *  3. The @Data annotation in Lombok bundles several features, including @ToString, @EqualsAndHashCode, @Getter, @Setter, and @RequiredArgsConstructor.
 *     However, when using @EqualsAndHashCode(callSuper = true), it does not automatically include the fields from the superclass in the toString() method.
 *     This is because the @Data annotation does not support configuring the @ToString parameters directly, such as callSuper or includeFieldNames.
 *
 *  4. To ensure that the fields from the superclass are included in the toString() method,
 *     you can explicitly use the @ToString annotation along with @Data and set callSuper = true.
 *     This will generate a toString() method that includes the superclass's toString() output, as well as the fields from the current class.
 *
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ExtendedPerson extends Person {
    private String occupation;
    private String department;
}
