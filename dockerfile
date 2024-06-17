# OpneJDK oficial
FROM openjdk:17-jdk-slim

# indica directorio en el contenedor
WORKDIR /app

# copia el compilado en el contenedor
COPY target/fintonic-0.0.1-SNAPSHOT.jar app.jar

# expone el puerto del contenedor
EXPOSE 8080

# ejecuta el compilado
ENTRYPOINT ["java", "-jar", "app.jar"]
