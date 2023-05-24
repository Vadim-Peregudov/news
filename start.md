# Getting Started
## Reference Documentation
This documentation provides instructions for installation,
configuration, and usage of the "News feed" application.
## Table of Contents

- [Configuration](#configuration)
- [Additional Files](#additional-files)
- [Usage](#usage)

## Configuration

<a id="configuration"></a>

1. Open the application.properties file in a text editor.
2. Set properties for connecting to the database:

``` properties 
spring.datasource.url=jdbc:mysql://localhost:3306/news
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

3. Set the properties of the image saving location.

```properties 
upload.dir.image=/home/news/feed/uploads
```

4. Save the changes.

## Additional Files
<a id="additional-files"></a>
`news.sql.gz`: This file contains the SQL script for creating the database and the necessary tables. Before running the
application, you need to import this file into your MySQL database. You can do this by following these steps:
1. Open your MySQL client (e.g., MySQL Workbench).
2. Create a new database named "news" (if it doesn't exist).
3. Import the extracted database.sql file into the "news" database.

You can also run a script to create a database and news table.
``` SQL
CREATE DATABASE IF NOT EXISTS news;

USE news;

CREATE TABLE IF NOT EXISTS news (
    id                    BIGINT AUTO_INCREMENT NOT NULL,
    title                 VARCHAR(255)          NULL,
    publication_date_time DATETIME              NULL,
    body                  TEXT                  NULL,
    image                 VARCHAR(255)          NULL,
    is_image_view         BIT(1)                NOT NULL,
    PRIMARY KEY (id)
);
```

## Usage

<a id="usage"></a>

1. After configuring the application.properties file and setting up the database, you need to build the project and
   package it into a WAR (Web Application Archive) file using the following Maven command:

```bash
mvn package
```

2. To run the project using Spring Boot and deploy the WAR file, you can use a servlet container such as Apache Tomcat.
   Here's an example of deploying the WAR file to Apache Tomcat:
    1. Copy the generated WAR file from the `target` directory to the `webapps` directory of your Apache Tomcat
       installation. Rename it like this `home.war`
    2. Start Apache Tomcat by running the appropriate startup script for your operating system.
    3. Open a web browser and access your application using the URL `http://localhost:8080/home`
3. To run the application without the Tomcat server, you can use the command.

```bash
mvn spring-boot:run
```

This command will start the Spring Boot application and deploy it locally for testing or development purposes.