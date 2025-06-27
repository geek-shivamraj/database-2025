## _JDBC_

1. javax.sql.DataSource
2. java.sql.DriverManager

* **JDBC Consolidated URL:** `jdbc:db_type://host:port/db_Name?user={userName}&password={password}`
  
| DataBase Vendor | Consolidated URL                         | driver                                         |
|-----------------|------------------------------------------|------------------------------------------------|
| MySQL           | `jdbc:mysql://host:port/db_Name`         | `com.mysql.cj.jdbc.Driver`                     |
| Oracle          | `jdbc:oracle:thin:@://host:port/db_Name` | `oracle.jdbc.OracleDriver`                     |
| SQL Server      | `jdbc:sqlserver://host:port/db_Name`     | `com.microsoft.sqlserver.jdbc.SQLServerDriver` |

* Important interfaces & Classes for JDBC API:- `Connection`, `Statement`, `PreparedStatement`, `ResultSet`, `CallableStatement`
*******************************************************************************************************************************************************************
## _Spring Boot JDBC_

1. JdbcTemplate
2. NamedParameterJdbcTemplate

## _Spring Boot Hibernate_
| DataBase Vendor | Dialect                               |
|-----------------|---------------------------------------|
| MySQL           | `org.hibernate.dialect.MySQL5Dialect` |
| Oracle          | `org.hibernate.dialect.OracleDialect` |

## _Spring Boot Mongo DB_

* Docker image pull command: docker pull mongo:8.0.10
  <br></br>
* Run Command: docker run --name mongo-container -d -p 27017:27017 <image_id>
  <br></br>
* When the repository package is on the different level to @SpringBootApplication / @EnableAutoConfiguration,
  then we need to explicitly add the following annotation to the main class in order for spring boot to
  read the mongo repositories.
* Reference link - https://stackoverflow.com/questions/29221645/cant-autowire-repository-annotated-interface-in-spring-boot

<p align="center">
  <img width="750" src="snips/img.png" alt="">
  <img width="750" src="snips/img_1.png" alt="">
</p>

## _@Builder Vs @SuperBuilder_
| No. | @Builder                                                                                                                                                                                                                                                    | @SuperBuilder                                                                                                                                                                                                                                                                                         |
|-----|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1.  | Functionality: @Builder is a stable lombok annotation that generates a builder API for a class. It allows creating object step-by-step by exposing methods for setting each field of the class                                                              | Functionality: @SuperBuilder is an experimental lombok annotation introduced in lombok 1.18. It extends the functionality of @Builder to support inheritance. It allows child classes to expose fields from their parent classes in their builder APIs without requiring additional boilerplate code. |
| 2.  | Limitations: It doesn't work well with Inheritance. If a child class can't directly access the parent's fields through its own builder unless additional workarounds are implemented, such as manually defining a constructor & annotating it with @Builder | Requirements: All classes in the inheritance hierarchy must be annotated with @SuperBuilder. This ensures that the builder for a child class can expose fields from all its superclass.                                                                                                               | 
| 3.  | Use Case: Ideal for classes that don't participate in an inheritance hierarchy or when working with older version of Lombok where @SuperBuilder is not available.                                                                                           | Use Case: Best suited for scenarios involving class hierarchies where you want to avoid manually writing constructors or boilerplate code to initialize fields from parent classes.                                                                                                                   |



