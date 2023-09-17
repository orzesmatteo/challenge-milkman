# Project Structure

The project is composed of different modules:

- __challenge-common__: Common library containing some constants used by other modules
- __challenge-dao__: Library containing JPA Entities
- __challenge-dto__: Library containing dto used as input and output for REST APIs
- __challenge-mapper__: Mapping library used to convert dao to dto one way or another
- __challenge-route-calculator__: Library containing different implementation of route calculator
- __challenge-server__: Actual Spring Boot Application importing all the other libraries, exposes Rest Controllers and
  beans responsible for the application business logic.

### Local

In order to run locally using docker Postgres use `./challenge-server/dependencies/docker-compose.yml` with
command `docker-compose up -d`

Otherwise, if you just want to run both the server and the db in you can use the docker-compose in the root directory.

### Docker Image

A public docker image repository of the application is available at
mtrz/challenge-server https://hub.docker.com/repository/docker/mtrz/challenge-server/general.

In order to build another version use the following command from root challenge directory.

`./mvnw clean`

`./mvnw package`

`cd challenge-server`

`docker build --tag challenge-server:your-version .`

### Infos

When application server is running description OpenApi is downloadable from http://localhost:8080/v3/api-docs, the same description is inside `api-docs.yml` file found in root directory. 

Default basic auth credentials are:

username: __challenge__

password: __challenge__