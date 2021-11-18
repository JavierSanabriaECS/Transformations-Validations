FROM openjdk:11
EXPOSE 8086
RUN mkdir -p /app/
ADD build/libs/Transformations-Validations-1.0-SNAPSHOT.jar /app/Transformations-Validations-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/Transformations-Validations-1.0-SNAPSHOT.jar"]