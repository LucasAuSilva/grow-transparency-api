FROM openjdk:17
WORKDIR /app
EXPOSE $PORT

COPY src src
COPY build.gradle .
COPY gradlew .
COPY gradle gradle
COPY settings.gradle .

RUN microdnf install findutils
RUN ./gradlew build

ENTRYPOINT ["./gradlew", "bootRun"]