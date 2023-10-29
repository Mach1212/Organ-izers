#
# Build stage
#
FROM amazoncorretto:21-alpine3.15-full AS build
RUN apk add maven
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME
RUN mvn clean install

#
# Package stage
#
FROM amazoncorretto:21-alpine3.15
ARG JAR_FILE=/usr/app/target/*.jar
COPY --from=build $JAR_FILE /app/runner.jar
EXPOSE 8080
ENTRYPOINT java -jar /app/runner.jar