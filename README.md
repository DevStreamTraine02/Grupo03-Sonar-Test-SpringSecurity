# Grupo03-Sonar-Test-SpringSecurity
# Aplicación de Seguridad con Spring Boot

Este proyecto es una aplicación de seguridad desarrollada con Spring Boot que incluye la autenticación de usuarios y la generación de tokens JWT.

## Ejecución del Proyecto

### Docker Compose

Para ejecutar la aplicación, primero asegúrate de tener Docker y Docker Compose instalados en tu sistema. Luego, ejecuta el siguiente comando en la raíz del proyecto:

docker-compose up

###Agregar roles en base de datos 
En una terminal ejecuta
docker exec -it postgres_db psql -U myuser -d mydatabase 

###Una ves dentro de la terminalde linux de docker 
Ejecuta el script 
\i ./scripts/insert_data.sql

### Para ejecutar jacoco
./mvwn  clean verify 
generar una carpeta llamada site dentro un index.html con el reporte de jacoco

### Para sonar ejecutar 

docker run -p 9000:9000 sonarqube:9.9.4-community 

 y luego
  ./mvnw clean verify sonar:sonar   -Dsonar.projectKey=key_de_tu_proyecto   -Dsonar.host.url=http://localhost:9000   -Dsonar.login=token_de_la_pagina





