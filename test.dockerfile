FROM amazoncorretto:17 AS build
WORKDIR /app
COPY build.gradle ./
COPY gradlew ./
COPY gradle/ gradle
COPY src/ src
RUN ./gradlew shadowJar

FROM amazoncorretto:19-alpine
WORKDIR /app
COPY --from=build /app/build/libs/munchy-server.jar ./
CMD ["java", "-jar", "munchy-server.jar"]