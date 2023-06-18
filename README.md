# Spring Boot JWT Authentication example with Spring Security & Spring Data JPA

## User Registration, User Login and Authorization process.
The diagram shows flow of how we implement User Registration, User Login and Authorization process.

![spring-boot-jwt-authentication-spring-security-flow](spring-boot-jwt-authentication-spring-security-flow.png)

## Spring Boot Server Architecture with Spring Security
You can have an overview of our Spring Boot Server with the diagram below:

![spring-boot-jwt-authentication-spring-security-architecture](spring-boot-jwt-authentication-spring-security-architecture.png)

For more detail, please visit:
> [Secure Spring Boot App with Spring Security & JWT Authentication](https://reactapp.com/spring-boot-jwt-authentication/)

> [For MongoDB](https://reactapp.com/spring-boot-jwt-auth-mongodb/)

## Refresh Token

![spring-boot-refresh-token-jwt-example-flow](spring-boot-refresh-token-jwt-example-flow.png)

For instruction: [Spring Boot Refresh Token with JWT example](https://reactapp.com/spring-boot-refresh-token-jwt/)

## More Practice:
> [Spring Boot File upload example with Multipart File](https://reactapp.com/spring-boot-file-upload/)

> [Exception handling: @RestControllerAdvice example in Spring Boot](https://reactapp.com/spring-boot-restcontrolleradvice/)

> [Spring Boot Repository Unit Test with @DataJpaTest](https://reactapp.com/spring-boot-unit-test-jpa-repo-datajpatest/)

> [Spring Boot Pagination & Sorting example](https://www.reactapp.com/spring-boot-pagination-sorting-example/)

Associations:
> [Spring Boot One To Many example with Spring JPA, Hibernate](https://www.reactapp.com/jpa-one-to-many/)

> [Spring Boot Many To Many example with Spring JPA, Hibernate](https://www.reactapp.com/jpa-many-to-many/)

> [JPA One To One example with Spring Boot](https://www.reactapp.com/jpa-one-to-one/)

Deployment:
> [Deploy Spring Boot App on AWS – Elastic Beanstalk](https://www.reactapp.com/deploy-spring-boot-aws-eb/)

> [Docker Compose Spring Boot and MySQL example](https://www.reactapp.com/docker-compose-spring-boot-mysql/)

## Fullstack Authentication

> [Spring Boot + Vue.js JWT Authentication](https://reactapp.com/spring-boot-vue-js-authentication-jwt-spring-security/)

> [Spring Boot + Angular 8 JWT Authentication](https://reactapp.com/angular-spring-boot-jwt-auth/)

> [Spring Boot + Angular 10 JWT Authentication](https://reactapp.com/angular-10-spring-boot-jwt-auth/)

> [Spring Boot + Angular 11 JWT Authentication](https://reactapp.com/angular-11-spring-boot-jwt-auth/)

> [Spring Boot + Angular 12 JWT Authentication](https://www.reactapp.com/angular-12-spring-boot-jwt-auth/)

> [Spring Boot + Angular 13 JWT Authentication](https://www.reactapp.com/angular-13-spring-boot-jwt-auth/)

> [Spring Boot + Angular 14 JWT Authentication](https://www.reactapp.com/angular-14-spring-boot-jwt-auth/)

> [Spring Boot + React JWT Authentication](https://reactapp.com/spring-boot-react-jwt-auth/)

## Fullstack CRUD App

> [Vue.js + Spring Boot + H2 Embedded database example](https://www.reactapp.com/spring-boot-vue-js-crud-example/)

> [Vue.js + Spring Boot + MySQL example](https://www.reactapp.com/spring-boot-vue-js-mysql/)

> [Vue.js + Spring Boot + PostgreSQL example](https://www.reactapp.com/spring-boot-vue-js-postgresql/)

> [Angular 8 + Spring Boot + Embedded database example](https://www.reactapp.com/angular-spring-boot-crud/)

> [Angular 8 + Spring Boot + MySQL example](https://reactapp.com/angular-spring-boot-crud/)

> [Angular 8 + Spring Boot + PostgreSQL example](https://reactapp.com/angular-spring-boot-postgresql/)

> [Angular 10 + Spring Boot + MySQL example](https://reactapp.com/angular-10-spring-boot-crud/)

> [Angular 10 + Spring Boot + PostgreSQL example](https://reactapp.com/angular-10-spring-boot-postgresql/)

> [Angular 11 + Spring Boot + MySQL example](https://reactapp.com/angular-11-spring-boot-crud/)

> [Angular 11 + Spring Boot + PostgreSQL example](https://reactapp.com/angular-11-spring-boot-postgresql/)

> [Angular 12 + Spring Boot + Embedded database example](https://www.reactapp.com/angular-12-spring-boot-crud/)

> [Angular 12 + Spring Boot + MySQL example](https://www.reactapp.com/angular-12-spring-boot-mysql/)

> [Angular 12 + Spring Boot + PostgreSQL example](https://www.reactapp.com/angular-12-spring-boot-postgresql/)

> [Angular 13 + Spring Boot + H2 Embedded Database example](https://www.reactapp.com/spring-boot-angular-13-crud/)

> [Angular 13 + Spring Boot + MySQL example](https://www.reactapp.com/spring-boot-angular-13-mysql/)

> [Angular 13 + Spring Boot + PostgreSQL example](https://www.reactapp.com/spring-boot-angular-13-postgresql/)

> [Angular 14 + Spring Boot + H2 Embedded Database example](https://www.reactapp.com/spring-boot-angular-14-crud/)

> [Angular 14 + Spring Boot + MySQL example](https://www.reactapp.com/spring-boot-angular-14-mysql/)

> [Angular 14 + Spring Boot + PostgreSQL example](https://www.reactapp.com/spring-boot-angular-14-postgresql/)

> [React + Spring Boot + MySQL example](https://reactapp.com/react-spring-boot-crud/)

> [React + Spring Boot + PostgreSQL example](https://reactapp.com/spring-boot-react-postgresql/)

> [React + Spring Boot + MongoDB example](https://reactapp.com/react-spring-boot-mongodb/)

Run both Back-end & Front-end in one place:
> [Integrate Angular with Spring Boot Rest API](https://reactapp.com/integrate-angular-spring-boot/)

> [Integrate React.js with Spring Boot Rest API](https://reactapp.com/integrate-reactjs-spring-boot/)

> [Integrate Vue.js with Spring Boot Rest API](https://reactapp.com/integrate-vue-spring-boot/)

## Dependency
– If you want to use PostgreSQL:
```xml
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <scope>runtime</scope>
</dependency>
```
– or MySQL:
```xml
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <scope>runtime</scope>
</dependency>
```
## Configure Spring Datasource, JPA, App properties
Open `src/main/resources/application.properties`
- For PostgreSQL:
```
spring.datasource.url= jdbc:postgresql://localhost:5432/testdb
spring.datasource.username= postgres
spring.datasource.password= 123

spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation= true
spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.PostgreSQLDialect

# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto= update

# App Properties
reactapp.app.jwtSecret= reactappSecretKey
reactapp.app.jwtExpirationMs= 86400000
```
- For MySQL
```
spring.datasource.url= jdbc:mysql://localhost:3306/testdb?useSSL=false
spring.datasource.username= root
spring.datasource.password= 123456

spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.ddl-auto= update

# App Properties
reactapp.app.jwtSecret= reactappSecretKey
reactapp.app.jwtExpirationMs= 86400000
```
## Run Spring Boot application
```
mvn spring-boot:run
```

## Run following SQL insert statements
```
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
```
