# 1. Базовый образ с Java (OpenJDK 17)
FROM openjdk:17-jdk-slim

# 2. Рабочая директория внутри контейнера
WORKDIR /app

# 3. Копируем собранный jar файл внутрь контейнера и называем app.jar
COPY build/libs/*.jar app.jar

# 4. Открываем порт 8080 для доступа к приложению
EXPOSE 8080

# 5. Команда запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]
