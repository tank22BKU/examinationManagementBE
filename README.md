# examinationManagementBE

A backend service for examination management built with:
- Java 21
- Spring MVC and Spring Data JPA (Jakarta imports)
- Lombok
- MapStruct
- Hibernate

This README explains how to configure the database and how to build/run the application.

## Prerequisites
- Java Development Kit (JDK) 21
- One build tool:
    - Gradle 8+ (if the project contains `build.gradle[.kts]`)
- A relational database (MySQL)
- Git (optional, for cloning)
- Lombok plugin enabled in your IDE (and Annotation Processing turned on)

## 1) Clone the repository

git clone `<this_project_url>`

## 2) Configure the database

Go to `/main/resource/application.yaml`

- Config `datasource: url: jdbc:mysql://localhost:<schemaport>/<schemaname>`
- Config `username: <yourusername>
    password: <yourpassword>`

## 3) Build Project
  
- `./gradlew clean build -x test`
- If any problems occur. Go to DataSchema and Delete All Rows in `flyway_schema_history` table. Then rebuild
- `./gradlew bootRun`
