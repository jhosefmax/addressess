FROM openjdk:14
COPY target/addresses-0.0.1-SNAPSHOT.jar /addresses.jar
CMD ["java", "-jar", "/addresses.jar"]
