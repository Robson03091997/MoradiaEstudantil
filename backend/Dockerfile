# Dockerfile para o Sistema de Locação de Imóveis
FROM openjdk:11-jre-slim

# Definir diretório de trabalho
WORKDIR /app

# Copiar o JAR da aplicação
COPY target/sistema-locacao-imoveis-1.0.0.jar app.jar

# Expor a porta 8080
EXPOSE 8080

# Comando para executar a aplicação
CMD ["java", "-jar", "app.jar"] 