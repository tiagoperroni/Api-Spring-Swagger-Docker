FROM openjdk:11

EXPOSE 8080

WORKDIR /src

COPY /target/*.jar /src/produto-api.jar

ENTRYPOINT [ "java", "-jar", "/src/produto-api.jar" ]