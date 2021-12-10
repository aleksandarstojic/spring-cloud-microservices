# Spring Cloud Microservices Project
This project was developed for the purposes of Distributed information systems subject at Faculty of Technical Sciences in Novi Sad. The idea was to create at least 5 microservices which communicate with each other, using Spring Cloud framework.

# Technologies used
Programming language = Java  
Framework = Spring Cloud  
Microservice communication library = Feign  
Circuit breaker = Resillience4j  
Rate limitter = Resillience4j  
Database = H2 in-memory database  
Service registry = Eureka naming server  
API gateway = Spring cloud gateway  
Centralized configuration = Spring cloud config server  
Distributed tracing = Zipkin  
Enhancing resiliency of distributed tracing = RabbitMQ  
API testing = Postman  
Versioning = Git  
Containerization = Docker  
Single command script for running all of the services together = Docker Compose  

# Microservices
- Student service
- Course service
- Student-Course service
- Department service
- Faculty service

Additional:
- Service registry (Eureka naming server)
- API gateway (Spring cloud gateway)
- Config server (Spring cloud config server)

# URI's

- Eureka naming server
=> http://localhost:8761/eureka  
 
- Zipkin
=> http://localhost:9411/zipkin  

- Config server
=> http://localhost:8888  

- Student service  
=> GET http://localhost:8000/students  
=> GET http://localhost:8000/students/{id}  
=> POST http://localhost:8000/students  
=> DELETE http://localhost:8000/students/{id}  

- Course service  
=> GET http://localhost:8100/courses  
=> GET http://localhost:8100/courses/{id}  
=> POST http://localhost:8100/courses  
=> DELETE http://localhost:8100/courses/{id}  

- Student-Course service  
=> GET http://localhost:8200/students-courses  
=> GET http://localhost:8200/courses-by-student/{id}  
=> GET http://localhost:8200/students-by-course/{id}  
=> POST http://localhost:8200/student/{id}/course{id}  
=> DELETE http://localhost:8200/student/{id}/course{id}  

- Department service  
=> GET http://localhost:8300/department  
=> GET http://localhost:8300/department/{id}  
=> POST http://localhost:8300/department  
=> DELETE http://localhost:8300/department/{id}  

- Faculty service  
=> GET http://localhost:8400/faculties  
=> GET http://localhost:8400/faculties/{id}  
=> POST http://localhost:8400/faculties  
=> DELETE http://localhost:8400/faculties/{id}  

- API gateway  
=> http://localhost:8765/{service-name}/{route}
- Example:  
=> http://localhost:8765/student-service/students

# Microservices diagram
![Microservices diagram](https://user-images.githubusercontent.com/26327440/145490744-1c2612dc-7bec-4842-969d-34205c85f94c.jpg)

# Description
Each microservice deals with certain data and has it's own database.  
Student service contains data about students.  
Course service contains data about courses and each course has the id of the department at which it is held.  
Student-Course service contains data about which students have signed up for which courses.  
Department service contains data about departments and each department has the id of the faculty it belongs to.  
Faculty service contains data about faculties.  

# Relations
Student can sign up for multiple courses and course can have multiple students signed up for it. (many-to-many)  
Course can be held at exactly one department, however one department can have multiple courses being held at it. (many-to-one)  
Department belongs to exactly one faculty, however one faculty can have multiple departments in it. (many-to-one)  

# Launching
To launch the application:  
1: Open Command Prompt and cd into the directory where you've downloaded the files from this repository  
2: Make sure that the docker-compose.yaml file is present (do not change it's name or extension)  
3: Start up the Docker Engine  
4: In Command Prompt run: docker-compose up  

# Testing
The collection of requests for Postman is provided in the Spring Cloud Microservices.postman_collection.json file.  
Postman's runner tool can also be used to test for rate limiting feature.  
Zipkin server provides distributed tracing and can also create dependency graph after a few requests have been fired.  
Eureka naming server's dashboard shows all the services that are up and running.  
