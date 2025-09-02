# --- 1. 빌드 스테이지 ---
FROM eclipse-temurin:17-jre as builder
WORKDIR /app
COPY . .
RUN chmod +x ./gradlew && ./gradlew build -x test
# --- 2. 최종 스테이지 ---
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
