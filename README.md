# Stand-up ticket shop
Implementation of an online store with default functionality written in Java.

## Structure

###### The project has an N-tier structure and consists of the layers:
* Database layer;
* DAO layer (JDBC API);
* Service layer(contains the business logic);
* Layer with controllers.

###### Clients can perform the following actions:

* register on the store's website;
* log in;
* look through events sold at the store;
* add tickets to their cart;
* delete tickets from the cart;
* place orders.

###### Admins in their turn can:

* view information about registered users;
* view all orders placed at the store;
* add new locations, events and sessions;
* modify exist sessions.

## Technologies 

* Java 11
* Maven 3.1.1
* Maven Checkstyle Plugin
* Hibernate
* MySQL
* Javax Servlet API
* Spring Framework
* Spring MVC
* Spring Security
* Apache Tomcat

## To start the project you need:

1. Download and install the JDK
2. Download and install web-server (for example Apache Tomcat)
3. Download and install MySQL. Setup connection properties in **db.properties** file
* user: "your username"
* password: "your password"
* db.url=jdbc:mysql://localhost/*your_db_name*?serverTimezone=EET
4. Run a project
