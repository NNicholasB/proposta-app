FROM eclipse-temurin:17-jdk

COPY target/proposta-app-0.0.1-SNAPSHOT.jar proposta-app-1.0.jar

ENTRYPOINT ["java","-jar","proposta-app-1.0.jar"]