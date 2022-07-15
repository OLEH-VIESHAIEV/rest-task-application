# Task application

#  Project Description
This simple application is an imitation of working Trello app with:
- creating, deleting, editing columns, displaying columns with opportunity to change order
- creating, deleting, editing tasks, moving to another column, displaying with opportunity to change order

##  Project Structure

This Project is based on 3-layered architecture:

- Data access layer (repository)

- Application layer (service)

- Presentation layer (controllers)

## Technologies:
- JAVA 11+
- Spring Boot, REST, Spring Data JPA, mvc
- Hibernate
- Git
- MySQL
- Maven

## System requirements

- IntelliJ IDEA Ultimate IDEA
- Postman
- Database (MySQL)

## Configure you env

- First, make sure your env meets requirements listed above
- Then clone project on your IDE
- Install Postman
- After loading app you can test app in Postman using next urls:

POST http://localhost:8080/columns (create column)

POST http://localhost:8080/tasks (create task)

PUT http://localhost:8080/tasks/1 (update task)

PUT http://localhost:8080/columns/1 (update column)

DELETE http://localhost:8080/tasks/1 (delete task)

DELETE http://localhost:8080/columns/1 (delete column)

GET http://localhost:8080/columns?sortBy=name:ASC (displaying columns by name order)

GET http://localhost:8080/columns?sortBy=name:DESC (same here, another order)
         also you can change order by column id

GET http://localhost:8080/tasks?sortBy=name:ASC (displaying tasks by name order)

GET http://localhost:8080/tasks?sortBy=name:DESC (same here, another order)
  also you can change order by other task fields