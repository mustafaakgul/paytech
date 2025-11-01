# Step-1: Build the application
FROM maven:3.8.1-openjdk-17-slim AS builder
RUN addgroup demogroup; adduser --ingroup demogroup --disabled-password demo
USER demo
WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN mvn clean install -Dmaven.test.skip=true
###

# Step-2: Runtime environment
FROM oracle:17-jre-jammy
WORKDIR /app
###

# Step-3: Copy the built jar file from the builder stage
COPY --from=builder /app/target/*.jar /app/app.jar
###

#Step-4: Run the application
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
###