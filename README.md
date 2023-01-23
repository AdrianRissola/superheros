# superheros
## Run the application without the Docker container
```
./mvnw package && java -Dspring.profiles.active=prod -jar target/superheros-0.0.1-snapshot.jar
```

## Containerize It
```
docker build -t springio/superheros .
```

## Build Docker Image with Maven
```
./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=springio/superheros
```

## Run docker image
```
docker run -e JAVA_OPTS="-Dspring.profiles.active=prod" -p 8080:8080 -t springio/superheros
```

## Display list of containers
```
docker ps
```

## shut it down
```
docker stop <containerID>
```

## Delete container
```
docker rm <containerID>
```
