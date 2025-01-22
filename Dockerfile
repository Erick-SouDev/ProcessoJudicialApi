# Use uma imagem base do JDK 17 (ou a versão que você estiver usando)

# Use uma imagem base do JDK 17 (ou a versão que você estiver usando)
FROM eclipse-temurin:17-jdk-alpine

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo JAR da aplicação para o diretório de trabalho no container
COPY target/*.jar /app/app.jar

# Exponha a porta que a aplicação Spring Boot usa
EXPOSE 8080

# Comando para executar a aplicação
CMD ["java", "-jar", "app.jar"]
