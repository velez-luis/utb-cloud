# Usa una imagen base de Java (OpenJDK 17 es común para aplicaciones modernas)
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR de la aplicación en el contenedor
COPY target/utb-cloud-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que tu aplicación escucha (8998 según tu configuración)
EXPOSE 8998

# Establece variables de entorno para la configuración de la base de datos
# Puedes definir estos valores en el archivo `docker-compose.yml` o pasarlos al ejecutar el contenedor
ENV SPRING_DATASOURCE_URL=jdbc:mysql://db_host:3306/todo?serverTimezone=America/Bogota&createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
ENV SPRING_DATASOURCE_USERNAME=todo-usr
ENV SPRING_DATASOURCE_PASSWORD=123456
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=create-drop
ENV SPRING_JACKSON_TIME_ZONE=America/Bogota
ENV SERVER_PORT=8998
ENV SERVER_SERVLET_CONTEXT_PATH=/api/v1

# Ejecuta la aplicación Java y permite que utilice las variables de entorno
CMD ["java", "-jar", "app.jar"]


