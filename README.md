# api-escola

Projeto para demonstrar um simples CRUD, sendo essa parte o backend REST com o Framework Java Spring.

[Frontend](https://github.com/mbissonho/spa-escola)

## Para executar:

1. Clone ou baixe o repositório.

2. Use o Docker para provisionar o Mysql ou use um local fazendo as devidas alterações no *application.yml*.

    `$ docker-compose up --build`

3. Após o Docker subir o Mysql, gere o jar.

    `$ mvn clean package`

4. Acesse o diretório /target.

    `$ cd target/`

5. Rode a aplicação que estará escutando em http://localhost:8080/.

    `$ java -jar api-0.0.1-SNAPSHOT.jar`

## Ambiente:

1. Maven 3.6.0
2. Openjdk 1.8.0_242



