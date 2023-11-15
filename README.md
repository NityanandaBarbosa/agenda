<p align="center">
  <a href="https://github.com/NityanandaBarbosa/agenda" target="blank"><img src="https://seeklogo.com/images/S/spring-logo-9A2BC78AAF-seeklogo.com.png" width="200" alt="Java Logo" /></a>
</p>

## Description

System for registering associates and creating and voting on agendas

## Requirements
- Java 17 installed

## Technologies
- Java
- Spring Boot
- JPA / Hibernate
- H2 Database
- JUnit
- Maven
- Swagger

## Installation

```bash
# paste this in your terminal
$ git clone git@github.com:NityanandaBarbosa/agenda.git
```


### 1 - Importing the project
##### 1.1 - Install/Update dependencies
Using the IDE of your choice and update the necessary packages for the project
##### 1.2 - Running the code
Basically what you should do, now is open AgendaApplication.java and  then execute the code.

#### How to test
##### 2 - Documentation | Test endpoints
Access Swagger(http://localhost:8080/swagger-ui/index.html) to test th endpoints
##### The application was divided into 4:
- Associates: registering a member is necessary to send the name, valid CPF and date of birth (/associate | POST)

- Agenda: To create an agenda, you only need to enter a title and a description. (/agenda | POST)<br />

With the agenda created, we can create a session so that members can vote.

- Session: to create an session, you must enter the id of an agenda to be voted on, and the session's closing date and time. The session's start date is the same as its creation. (/session | POST)<br />
Note: An agenda can only be linked to one session.

- Vote: To cast a vote, you must enter the member ID and session ID and your vote (Yes or No). (/vote | Post)<br />
After the session is FINISHED, it is possible to count votes. (/vote/{session_id} | GET)

## Stay in touch

- Author - Nityananda Barbosa
