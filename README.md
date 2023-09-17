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

When application server is running description OpenApi is downloadable from http://localhost:8080/v3/api-docs, the same
description is inside `api-docs.yml` file found in root directory.

Default basic auth credentials are:

username: __challenge__

password: __challenge__

By default, some entities are inserted on db creation for future usage:

- Depots:
    - __Morciano-Depot__ `e45dc9fd-72e1-45dc-881a-a3c53fa07b21`
    - __Riccione-Depot__ `bae47020-edcd-4daa-9405-38b062367b94`
- Suppliers:
    - __Supplier-1__ `a4f7bdcc-16fd-4449-91a8-be5122c9a09a`
    - __Supplier-2__ `f35944b1-f082-458c-9571-e71a6a5f8738`