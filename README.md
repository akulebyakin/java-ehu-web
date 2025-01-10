# java-ehu-web

EHU student Java EE project

## Project Overview

This project is a web application for managing a list of coffee shops. Users can register, log in, view the list of coffee shops, and log out. Admin users have additional privileges to edit or delete any coffee shop by its ID.

## Features

- **User Registration**: Users can register by providing their name, email, login, and password.
- **User Login**: Registered users can log in to the system.
- **View Coffee Shops**: Users can view a list of coffee shops with their name, address, contact data, and rating.
- **Admin Privileges**: Admin users can edit or delete any coffee shop by its ID.
- **Error Handling**: The application displays error messages for various scenarios, such as invalid login credentials or registration errors.
- **Validation**: Registration fields are validated to ensure data integrity.
- **Security**: Passwords are stored securely using the BCrypt hashing algorithm.
- **Database Integration**: The application uses PostgreSQL as the database backend. Connection pooling is implemented.

## Prerequisites

1. **Java**:
    - Install [Java 8 or later](https://www.oracle.com/java/technologies/javase-downloads.html).
    - Verify installation by running:
      ```bash
      java -version
      ```

2. **Maven**:
    - Install [Apache Maven](https://maven.apache.org/install.html).
    - Verify installation by running:
      ```bash
      mvn -version
      ```

3. **Apache Tomcat**:
    - Download and install [Apache Tomcat 9 or later](https://tomcat.apache.org/).
    - Set up the `CATALINA_HOME` environment variable to point to your Tomcat installation.

4. **PostgreSQL**:
    - Install [PostgreSQL](https://www.postgresql.org/download/).
    - Verify installation by running:
      ```bash
      psql --version
      ```

## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/java-ehu-web.git
   cd java-ehu-web
   ```

2. **Configure Database Properties**:
    - Open the `src/main/resources/db.properties` file.
    - Set the following properties:
      ```properties
      db.url=jdbc:postgresql://localhost:5432/your_database
      db.username=your_username
      db.password=your_password
      db.driverClassName=org.postgresql.Driver
      db.poolSize=8
      ```

3. **Initialize the Database**:
    - Start the PostgreSQL service.
    - Create the database schema by executing the provided SQL script:
      ```bash
      psql -U your_username -d your_database -f src/main/resources/sql/createUsersTable.sql
      ```

4. **Build the Application**:
    - Use Maven to build the project:
      ```bash
      mvn clean install
      ```

5. **Deploy to Tomcat**:
    - Copy the generated WAR file from the `target` directory (e.g., `java_ehu_web.war`) to the `webapps` directory of your Tomcat installation.
    - Start Tomcat by running:
      ```bash
      $CATALINA_HOME/bin/startup.sh
      ```
    - Access the application at: [http://localhost:8080/java_ehu_web](http://localhost:8080/java_ehu_web).

## File Structure

- **`db.properties`**: Configuration file for database connection.
- **`sql/*.sql`**: SQL scripts to create necessary tables.
- **`src/main/java`**: Java source code for the application.
- **`src/main/resources`**: Configuration files and resources.

## License

This project is licensed under the MIT License. See the [MIT LICENSE](https://opensource.org/licenses/MIT) for details.

