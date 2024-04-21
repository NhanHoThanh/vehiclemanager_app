# Start with a base image containing Java runtime and Maven
FROM maven:3.8.4-openjdk-17-slim as build
# Make port 8080 available to the world outside this container
EXPOSE 8080

# Set the working directory in the image
WORKDIR /app

# Copy the pom.xml file
COPY ./drivers/pom.xml ./pom.xml

# Download the dependencies
RUN mvn dependency:go-offline -B -X

# Copy the source code
COPY ./drivers/src ./src

# Copy the serviceAccount.json file
# Copy the serviceAccount.json file
COPY ./drivers/src/main/resources/serviceAccount.json /app/resources/serviceAccount.json
# Build the project
RUN mvn clean package

RUN find /

# Specify the start-up command
CMD ["mvn", "spring-boot:run", "-e", "-X"]