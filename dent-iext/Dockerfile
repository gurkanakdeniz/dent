FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD

COPY pom.xml /dent-iext-build/

COPY src /dent-iext-build/src/

WORKDIR /dent-iext-build/

RUN mvn package -Dmaven.test.skip=true 

FROM openjdk:8-jre-alpine

WORKDIR /dent-iext

EXPOSE 8083

COPY --from=MAVEN_BUILD /dent-iext-build/target/*.jar dent-iext.jar

ENTRYPOINT ["java","-jar","/dent-iext/dent-iext.jar"]

