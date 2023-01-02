# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.1/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.1/reference/htmlsingle/#web)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.0.1/reference/htmlsingle/#using.devtools)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)

### Garage

In this problem, you have a garage that can be parked up to 10 slots (you can consider each slot is 1 unit range) at any
given point in time. You should create an automated ticketing system that allows your customers to use your garage
without human intervention. When a car enters your garage, you give a unique ticket issued to the driver. The ticket
issuing process includes us documenting the plate and the colour of the car and allocating an available slots to the car
before actually handing over a ticket to the driver. When a vehicle holds number of slots with its own width, you have to
leave 1 unit slot to next one. The customer should be allocated slot(s) which is nearest to the entry. At the exit the
customer returns the ticket which then marks slot(s) they were using as being available.
Create a spring boot project and then, publish a rest controller. Your controller methods include park, leave and status.

### Postman Collections

* [Postman Collection](/postman/Garage_Collection.postman_collection.json)

### Swagger

* *Swagger Host*
    * http://localhost:8080/swagger-ui/index.html#/ </br>
* *Api document*
    * http://localhost:8080/apidoc </br>


#### Dikkat Edilen Hususlar

* Design Pattern
* Exception handling
* Validation & loglama
* Unit test
* Thread safe
* In-memory çözüm üretilmeli, DB kullanılmamalı.