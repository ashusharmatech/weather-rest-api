### Prerequisites
- JDK 1.8+  
- Maven

### Technology stack:
* Spring Boot
* Spring Data JPA
* H2
* Spring Security
* JWT (JSON Web Tokens)

There is one user accounts already created:
```
Admin - admin:admin
```

### Local Run:
* The Spring Boot Maven plugin includes a run goal that can be used to quickly compile and run your application. Applications run in an exploded form, as they do in your IDE. The following example shows a typical Maven command to run a Spring Boot application:
```
$ mvn spring-boot:runspring-boot-maven-plugin
```

* You might also want to use the MAVEN_OPTS operating system environment variable, as shown in the following example:
```
$ export MAVEN_OPTS=-Xmx1024m
```
More details at: https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html


### Creating an Executable Jar

Before we begin, open a terminal and run the following commands to ensure that you have valid versions of Java and Maven installed:
```
$ java -version
java version "1.8.0_102"
Java(TM) SE Runtime Environment (build 1.8.0_102-b14)
Java HotSpot(TM) 64-Bit Server VM (build 25.102-b14, mixed mode)
```
```
$ mvn -v
Apache Maven 3.5.4 (1edded0938998edf8bf061f1ceb3cfdeccf443fe; 2018-06-17T14:33:14-04:00)
Maven home: /usr/local/Cellar/maven/3.3.9/libexec
Java version: 1.8.0_102, vendor: Oracle Corporation
```
* Run
```
mvn package 
```
from the command line, once this command is successfully completed. 
Target folder will have the executable jar file

* Run
```
java -jar target\weather-0.0.1-SNAPSHOT.jar
```
It will start the application.