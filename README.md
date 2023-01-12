# Problem
In cooperativism, each associate has one vote and decisions are taken in assemblies, by vote. Imagine that you must create a mobile solution to manage and participate in these voting sessions.

This solution must run in the cloud and promote the following functionalities through a REST API:

* Register a new agenda
* Open a voting session on an agenda (the voting session must be open for a certain amount of time in the opening call or 1 minute by default)
* Receive member votes on agendas (votes are 'Yes'/'No' only. Each member is identified by a unique id and can only vote once per agenda)
* Count the votes and put the result of the vote on the agenda

# Solution - votacao-api

* Microservices
* Spring Boot
* Docker & Docker Compose
* REST API
* MongoDB
* Spring Data MongoDB

![alt text](http://josivansilva.com/roles-api.png)

# How to run the code
The following was discovered as part of building this project:

* Assuming you already installed docker and docker compose, perform the actions below
* Open terminal and run
* cd in the user's home folder
* mkdir dev && cd dev
* mkdir docker && cd docker
* mkdir mongodb && cd mongodb
* Locate a docker-compose.yml file from the root folder of the project roles-api and copy it to the “mongodb” directory you just created
* Open terminal, make sure you are still inside the “mongodb” folder and run: docker-compose up -d
* MongoDB Compass was used as a MongoDB client
* Postman was used to perform API tests

