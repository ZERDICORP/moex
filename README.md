# moex
#### RESTful service for storing and searching for moex securities.

## Technology stack :wrench:
* ***Scala*** (v2.13.8)
  - ***Akka-Http*** (v10.2.4)
  - ***Akka-Stream*** (v2.6.14)
  - ***Slick*** (v3.3.3)
  - ***Cats*** (v2.8.0)
  - ***Slf4j*** (v1.7.5)
* ***PostgreSQL*** (v14.0)
* ***SBT*** (v1.7.2)
* ***Docker-Compose*** (v2.6.1)
* ***Liquibase*** (v4.13.0)

## Architecture Solution
The chosen solution is based on the classic layered architecture  

![image](https://user-images.githubusercontent.com/56264511/194931012-43eae9f6-6a46-4cd8-ad66-b533f801228d.png)
- ***Presentation Layer*** represented as ***controllers***
- ***Business Layer*** represented as ***services***
- ***Persistence Layer*** represented as ***repositories***
- ***Database Layer*** represented as ***PostgreSQL***

## «Import and Parsing XML» Solution
Importing and parsing xml works based on message queue and scheduler.  
The `xml` table in the database is used as the message queue.  
And as a scheduler, the mechanism (Scheduler) built into akka http.

## Launch Guide
#### 1. Open the `local/infra` folder and run the following command:
```
$ sudo docker-compose up
```
#### 2. Open the `src/main/resources/liquibase` folder and run the following command:
```
$ liquibase update-count 1
```
#### 3. Now you can start the project (i'm using IntellijIDEA)
