# Project Structure

The project is composed of different modules:

- __challenge-common__: Common library containing some constants used by other modules
- __challenge-dao__: Library containing JPA Entities
- __challenge-dto__: Library containing dto used as input and output for REST APIs
- __challenge-mapper__: Mapping library used to convert dao to dto one way or another
- __challenge-route-calculator__: Library containing different implementation of route calculator
- __challenge-server__: Actual Spring Boot Application importing all the other libraries, exposes Rest Controllers and
  beans responsible of the application business logic.

### TODOS

- complete business logic
- introduce controller advice to handle exceptions

### EXTRAS

- introduce dynamic cors configuration
- add aspect module for auditing purposes
- add scheduled method to declare packages as lost after some time
