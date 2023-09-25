# Establece la imagen base (OpenJDK 20)
FROM openjdk:20-jdk-slim as builder

WORKDIR app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM openjdk:20-jdk-slim
WORKDIR app

RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring

# Copiar las capas del JAR
COPY --from=builder app/dependencies/ ./
COPY --from=builder app/spring-boot-loader/ ./
COPY --from=builder app/snapshot-dependencies/ ./
COPY --from=builder app/application/ ./



ENV DATABASE_URL=jdbc:postgresql://localhost:5432/Ejemplo_arqui

EXPOSE 8083
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
