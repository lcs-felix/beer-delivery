# Beer Delivery

App que encontra um ponto de venda mais próximo da sua casa, 
para entregar cerveja gelada de maneira rápida!

Tecnolgias

- Java 10
- Maven
- Postgres com Postgis
- Docker

# Rodando localmente

Para rodar localmente, você pode executar o Docker Compose para subir 
o Postgres com o Postgis:

```
docker-compose -f docker-compose-dev.yml up -d
``` 

Em seguida é só acessar o diretório do projeto e rodar o plugin do 
Maven do Spring Boot:

```
$ cd beer-delivery
$ mvn spring-boot:run
``` 

Agora é só acessar [http://localhost:8080/graphiql](http://localhost:8080/graphiql) ;)

# Deploy

O arquivo `docker-compose.yml` é responsável pelos containers 
do Postgres com Postgis e de uma imagem com a aplicação.

Seria possível configurar a aplicação em um servidor 
de integração contínua para que buildar a aplicação e deployar
em algum serviço. 

O comando `mvn deploy` builda a aplicação e a imagem do Docker 
que a contém e envia pro [Dockerhub](https://hub.docker.com/r/lucasfelix/beer-delivery/tags/). 