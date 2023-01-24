FROM amazoncorretto:17

WORKDIR /app

COPY gradle/ gradle
COPY src/ src
COPY build.gradle ./
COPY gradlew ./

CMD ["./gradlew", "run"]
