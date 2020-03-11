# CRM 
## About
This project is a Customer Relationship Management system, intended to simplify the work of managers in extreme sports 
campground

## Installation
1. Install JDK 11 or later
2. Import ***here should be link on .sql file*** to your MySQL DB
3. After having cloned the repository, run the following commands at the repository root by your OS:
    ### Linux
        ./mvnw clean install
        ./mvnw spring-boot:run
    ### Windows 
        ./mvnw.cmd clean install
        ./mvnw spring-boot:run
4. Assert **Whitelabel Error Page** on http://localhost:8080/
     
## Run with Docker
1. Install docker [from here](https://docs.docker.com/install/)
2. Run from project folder `docker-compose up`
3. Application serve at `http://localhost:8888`
4. Database will be host at `localhost:3333`