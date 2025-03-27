FROM openjdk:17-jdk-slim

WORKDIR /app

COPY library-book-management-system/.mvn .mvn
COPY library-book-management-system/mvnw .
COPY library-book-management-system/pom.xml .
COPY library-book-management-system/src ./src

RUN chmod +x ./mvnw

RUN ./mvnw clean package -DskipTests

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "./target/library-book-management-system-0.0.1-SNAPSHOT.jar"]