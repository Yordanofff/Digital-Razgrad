FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve

COPY src ./src
EXPOSE 8080
CMD ["./mvnw", "spring-boot:run"]


#
# Build stage
#
#FROM eclipse-temurin:21-jdk-jammy AS build
#ENV HOME=/usr/app
#RUN mkdir -p $HOME
#WORKDIR $HOME
#ADD . $HOME
#RUN --mount=type=cache,target=/root/.m2 ./mvnw -f $HOME/pom.xml clean package

#
# Package stage
#
#FROM eclipse-temurin:21-jdk-jammy
#ARG JAR_FILE=/usr/app/target/*.jar
#COPY --from=build $JAR_FILE /app/runner.jar
#EXPOSE 8080
#ENTRYPOINT java -jar /app/runner.jar