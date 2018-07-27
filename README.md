# Descrição do problema 

```sh
A XY Inc. é uma empresa especializada na produção de excelentes receptores GPS (Global Positioning System). A diretoria está empenhada em lançar um dispositivo inovador que promete auxiliar pessoas na localização de ponto de interesse (POIs), e precisa muito de sua ajuda. 
 
Você foi contratado para desenvolver a plataforma que fornecerá toda a inteligência ao dispositivo. Esta plataforma deve ser baseada em serviços, de forma a flexibilizar a integração, sendo estes:  

Serviço para cadastrar pontos de interesse com 3 atributos: Nome do POI, Coordenada X (inteiro não 
negativo) Coordenada Y (inteiro não negativo). Os POIs devem ser armazenados em uma base de dados. 

Serviço para listar todos os POIs cadastrados.  

Serviço para listar POIs por proximidade. Este serviço receberá uma coordenada X e uma c oordenada Y, especificando um ponto de referência, em como uma distância máxima (d- max) em metros. O serviço deverá retornar todos os POIs da base de dados que estejam a uma distância menor ou igual a d-max a partir do ponto de referência. Exemplo:  

Base de Dados:  

'Lanchonete' (x=27, y=12)  
'Posto' (x=31, y=18)  
'Joalheria' (x=15, y=12)  
'Floricultura' (x=19, y=21)  
'Pub' (x=12, y=8)  
'Supermercado' (x=23, y=6)  
'Churrascaria' (x=28, y=2)  
Dado o ponto de referência (x=20, y=10) indicado pelo receptor GPS, e uma distância máxima de 10 metros, 
o serviço deve retornar os seguintes POIs:   

Lanchonete 
Joalheria 
Pub 
Supermercado
```
 
## 1. Dependências

Instalar as seguintes ferramentas:

    1. JDK 1.8
    2. Maven
    3. Docker compose
    
O projeto utiliza um banco embarcado (Postgres) para desenvolvimento.
 
## 2. Executando o Projeto
 IDEA Intellij pode ser recomendado
Após baixar o projeto, para executá-lo é necessário rodar os seguintes comandos dentro da pasta raiz.

```sh
$ docker-compose up   // cria a tabela xy-inc e realiza 7 insert para começar a testar os serviços de forma automatizada.
$ mvn clean install   
$ mvn spring-boot:run 
```
## 3. Testando os serviços
Realizar a chamada dos serviços. 
Para testar os serviços utilize um browser ou o postman


GET / - Lista todos os produtos de POIs  com paginação 
```sh
$ curl localhost:8080/pois?page=0&size=4
{
    "content": [
        {
            "id": 1,
            "name": "Lanchonete",
            "coordinatedX": 27,
            "coordinatedY": 12
        },
        {
            "id": 2,
            "name": "Posto",
            "coordinatedX": 31,
            "coordinatedY": 18
        },
        {
            "id": 3,
            "name": "Joalheria",
            "coordinatedX": 15,
            "coordinatedY": 12
        },
        {
            "id": 4,
            "name": "Floricultura",
            "coordinatedX": 19,
            "coordinatedY": 21
        }
    ],
    "last": false,
    "totalElements": 7,
    "totalPages": 2,
    "sort": null,
    "first": true,
    "numberOfElements": 4,
    "size": 4,
    "number": 0
}
```

GET - Busca todos os POIs com uma max-dist= 10 
```sh
O Postman pode ser recomendado:
$ curl http://localhost:8080/pois/distance?coordinateReferenceX=20&coordinateReferenceY=10&distance=10
{
    "content": [
        {
            "id": 1,
            "name": "Lanchonete",
            "coordinatedX": 27,
            "coordinatedY": 12
        },
        {
            "id": 3,
            "name": "Joalheria",
            "coordinatedX": 15,
            "coordinatedY": 12
        },
        {
            "id": 5,
            "name": "Pub",
            "coordinatedX": 12,
            "coordinatedY": 8
        },
        {
            "id": 6,
            "name": "Supermercado",
            "coordinatedX": 23,
            "coordinatedY": 6
        }
    ],
    "last": true,
    "totalPages": 1,
    "totalElements": 4,
    "sort": null,
    "first": true,
    "numberOfElements": 4,
    "size": 10,
    "number": 0
}
```

POST - Cria um novo POIs 
O Postman pode ser recomendado:
```sh
$ curl -H "Content-Type: application/json" -X POST -d '

http://localhost:8080/pois

{  
  "name": "ZupTI",
  "coordinatedX": 23, 
  "coordinatedY": 4
}

```
## 4.Documentação Swagger

```sh
documentação dos serviços. URL http://localhost:8080/swagger-ui.html

```

## 5.Encerrar o docker
```sh
$ docker-compose down

```
