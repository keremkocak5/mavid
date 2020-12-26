## Link Coverter 

This is the project to be an URL to Deeplink converter. At this stage there are no endpoints initialized. 

### Running the service

The command below can be used to run the application:
```
mvn spring-boot:run
```


### Api Documentation

You may access [Swagger UI](http://localhost:8080/swagger-ui.html) to display the available API's. 

### Security

Spring Web Security ensures authorization for accessing web services. In this demo application, username and password are embedded into SecurityConfig class. 


### Test Coverage

Test coverage is reported with Jacoco. Execute the following Maven command the generate Jacoco report:
```
mvn clean install
```

The coverage file will be generated in your local directory ".../linkconverter/target/site/jacoco/index.html" 

### Project Dependencies 
- Java 1.8.0_131 
- Spring Boot 2.4.1
- maven 3.6.3
- Swagger 2.4.0
- Junit 5
- Jacoco 0.8.6
- MySQL connection, as specified in "Running the MSQL Database" section.

### Running the MSQL Database

The connection parameters of MySQL Database must be specified in the application.properties file. The following line can be executed on MySQL server to create 'trendyoltest' DB. 

```
CREATE DATABASE trendyoltest;
```
