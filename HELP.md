# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/4.0.0-SNAPSHOT/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/4.0.0-SNAPSHOT/maven-plugin/build-image.html)
* [Docker Compose Support](https://docs.spring.io/spring-boot/4.0.0-SNAPSHOT/reference/features/dev-services.html#features.dev-services.docker-compose)
* [Spring Web](https://docs.spring.io/spring-boot/4.0.0-SNAPSHOT/reference/web/servlet.html)
* [HTTP Client](https://docs.spring.io/spring-boot/4.0.0-SNAPSHOT/reference/io/rest-client.html#io.rest-client.restclient)
* [Spring HATEOAS](https://docs.spring.io/spring-boot/4.0.0-SNAPSHOT/reference/web/spring-hateoas.html)
* [Thymeleaf](https://docs.spring.io/spring-boot/4.0.0-SNAPSHOT/reference/web/servlet.html#web.servlet.spring-mvc.template-engines)
* [JDBC API](https://docs.spring.io/spring-boot/4.0.0-SNAPSHOT/reference/data/sql.html)
* [Flyway Migration](https://docs.spring.io/spring-boot/4.0.0-SNAPSHOT/how-to/data-initialization.html#howto.data-initialization.migration-tool.flyway)
* [Spring Data JPA](https://docs.spring.io/spring-boot/4.0.0-SNAPSHOT/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Spring Security](https://docs.spring.io/spring-boot/4.0.0-SNAPSHOT/reference/web/spring-security.html)
* [Validation](https://docs.spring.io/spring-boot/4.0.0-SNAPSHOT/reference/io/validation.html)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/4.0.0-SNAPSHOT/reference/actuator/index.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Building a Hypermedia-Driven RESTful Web Service](https://spring.io/guides/gs/rest-hateoas/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)
* [Accessing Relational Data using JDBC with Spring](https://spring.io/guides/gs/relational-data-access/)
* [Managing Transactions](https://spring.io/guides/gs/managing-transactions/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)

### Docker Compose support
This project contains a Docker Compose file named `compose.yaml`.
In this file, the following services have been defined:

* mysql: [`mysql:latest`](https://hub.docker.com/_/mysql)

Please review the tags of the used images and set them to the same as you're running in production.

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

