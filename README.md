# Dockerización del Proyecto de Spring Boot - FINTONIC

## Prerrequisitos

- Docker instalado: [Instrucciones de instalación](https://docs.docker.com/get-docker/)
- Docker Compose instalado: [Instrucciones de instalación](https://docs.docker.com/compose/install/)
- Java 17 instalado
- Maven instalado

## Paso 1: Construir el JAR de la aplicación

Primero, debes construir el JAR de la aplicación utilizando Maven. Ejecuta el siguiente comando en la raíz del proyecto:

```
./mvnw clean package
```

## Paso 2: Ejecutar con Docker Compose

Simplemente ejecuta el siguiente comando para levantar tanto el contenedor de MongoDB como el contenedor de tu aplicación Spring Boot:

```
docker-compose up
```

## Paso 5: Verificar la Ejecución

Abre tu navegador y navega a http://localhost:8080/tasks para verificar que la aplicación esté funcionando correctamente. También puedes usar herramientas como Postman o curl para interactuar con el API.
