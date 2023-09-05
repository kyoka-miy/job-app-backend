# Job-App-Backend

## Description

Backend of Job application management web application (Java, Spring, Postgres, Docker)

## requirements

- JDK 17

## Prod
Deployed by using Railway https://job-app-backend-production.up.railway.app/

## How to Run Locally
Open Docker first, then
```
docker-compose up -d
```
Login Postgres (password is 'password')
```
psql -h localhost -U postgres
```
Create Database
```
postgres=# create database job_app;
```
Run the application
```
mvn clean install
```
Login Database
```
 psql -h localhost -U postgres -d job_app
```

## Feature
- Login Form with token authentication (Sign up, mail confirmation, log in)
- CRUD operation for User and Application
- URL Authentication using Spring Security
- Error Handling
- Request Validation (Email)
- Unit Test

