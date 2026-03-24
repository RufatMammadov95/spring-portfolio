FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# JAR copy
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# SQLite
RUN mkdir -p /data

# Environment variable (DB path)
ENV SPRING_DATASOURCE_URL=jdbc:sqlite:/data/portfolio.db

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]