FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD

COPY pom.xml /dent-bff-build/

COPY src /dent-bff-build/src/

WORKDIR /dent-bff-build/

RUN mvn package -Dmaven.test.skip=true 

FROM openjdk:8-jre-alpine

WORKDIR /dent-bff

EXPOSE 8081

COPY --from=MAVEN_BUILD /dent-bff-build/target/*.jar dent-bff.jar

ENTRYPOINT ["java","-jar","/dent-bff/dent-bff.jar"]