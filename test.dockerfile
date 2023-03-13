FROM amazoncorretto:17

WORKDIR /app

COPY build.gradle ./
COPY gradlew ./
COPY gradle/ gradle
COPY src/ src

CMD ["./gradlew", "run"]
