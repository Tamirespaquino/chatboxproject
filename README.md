# ChatBox Project

Project developed to learn more about Microservices and Java programming language

## Screens

### Intro
![Intro](/img/intro.png )

### Login
![Login](/img/login.png )

### Register
![Register](/img/register.png )

### Chat
![Chat](/img/exChat.png )

## Diagram

![ChatBoxDiagram](/img/chatImg.png )

## Microservices

* *Front-end*: users' interaction

* *Producer*: sends message to RabbitMQ and communicates with Front-end using WebSocket

* *Consumer*: receives message from RabbitMQ and saves at MongoDB. Also has a dead letter queue configuration

* *Service*: connects to MongoDB and searches users/messages

## Technologies:

* MongoDB
* RabbitMQ
* WebSocket
* Spring Boot
* Spring Security

## Request

### POST:
* **/send-message**: Send message to RabbitMQ

```bash
curl --location --request POST 'http://localhost:8080/send-message' \
--header 'Content-Type: application/json' \
--data-raw '{
    "sender":"João",
    "receiver":"Maria",
    "message": "Olá, Maria, tudo bem?"
}'
```

Response:
```bash
Status 200 OK

Message has been sent successfully!
```


* **/users/register**: User registration and saved in database
```bash
curl --location --request POST 'http://localhost:9091/users/register' \
--header 'Authorization: Bearer 8b16ac99-4068-4698-a6a3-ca59ab71f32a' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "MariSa2",
    "firstname": "Maria",
    "lastname": "da Silva",
    "password": "1234",
    "confirmPassword": "1234",
    "email": "maria2@gmail.com",
    "cpf": "12234567803",
    "address": "Av das Naçoes, 123, São Paulo, SP"
}'
```

Response:
```bash
Status 200 OK

Message has been sent successfully!
```


* **/users/login**: User log in with token authentication and password encryption
```bash
curl --location --request POST 'http://localhost:9091/users/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username":"MariSa2",
    "password":"1234"
}'
```

Response:
```bash
Status 200 OK

{
    "type": "Bearer",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJJUlMiLCJzdWIiOiI2MzMyNTcwMjZmNzY0NzViYzI0MzAzOGQiLCJpYXQiOjE2NjQyODcwNzYsImV4cCI6MTY2NDI4ODg3Nn0.Ux4RVd9q9EHDVuA6EaLTSn8E5SugaiqCVczRRR6Lxzo"
}
```


### GET:
* **/messages**: Find all messages using a filter by id, username and date
```bash
curl --location --request GET 'http://localhost:9091/messages' \
--header 'username: tamires' \
--header 'begin_create_date: 25-09-2022' \
--header 'end_create_date: 26-09-2022' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJJUlMiLCJzdWIiOiI2MzMyNTcwMjZmNzY0NzViYzI0MzAzOGQiLCJpYXQiOjE2NjQyODcwNzYsImV4cCI6MTY2NDI4ODg3Nn0.Ux4RVd9q9EHDVuA6EaLTSn8E5SugaiqCVczRRR6Lxzo'
```

Response:
```bash
Status 200 OK

{
        "message": "ioioio",
        "sender": "tamires",
        "receiver": "joao",
        "createDate": "2022-09-26T13:22:47.793262100"
    },
    {
        "message": "vfdfbf",
        "sender": "joao",
        "receiver": "tamires",
        "createDate": "2022-09-26T13:22:53.495353900"
    },
    {
        "message": "oi, joao",
        "sender": "tamires",
        "receiver": "joao",
        "createDate": "2022-09-26T13:31:01.496713"
    },
    {
        "message": "oi, tamires",
        "sender": "joao",
        "receiver": "tamires",
        "createDate": "2022-09-26T13:31:07.834276800"
}
```

* **/users**: Find all users registered

```bash
curl --location --request GET 'http://localhost:9091/users' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJJUlMiLCJzdWIiOiI2MzMyNTcwMjZmNzY0NzViYzI0MzAzOGQiLCJpYXQiOjE2NjQyODcwNzYsImV4cCI6MTY2NDI4ODg3Nn0.Ux4RVd9q9EHDVuA6EaLTSn8E5SugaiqCVczRRR6Lxzo'
```

Response:
```bash
Status 200 OK
[
    {
        "firstname": null,
        "lastname": null,
        "username": null,
        "password": null,
        "confirmPassword": null,
        "email": null,
        "cpf": null,
        "address": null
    },
    {
        "firstname": null,
        "lastname": null,
        "username": null,
        "password": null,
        "confirmPassword": null,
        "email": null,
        "cpf": null,
        "address": null
    },
    {
        "firstname": null,
        "lastname": null,
        "username": null,
        "password": null,
        "confirmPassword": null,
        "email": null,
        "cpf": null,
        "address": null
    }
]
```

* **/users/id**: Find user by id

```bash
curl --location --request GET 'http://localhost:9091/users/6331d4d45959346e986ecab2' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJJUlMiLCJzdWIiOiI2MzMyNTcwMjZmNzY0NzViYzI0MzAzOGQiLCJpYXQiOjE2NjQyODcwNzYsImV4cCI6MTY2NDI4ODg3Nn0.Ux4RVd9q9EHDVuA6EaLTSn8E5SugaiqCVczRRR6Lxzo'
```

Response:
```bash
Status 200 OK

{
    "id": "633257026f76475bc243038d",
    "firstname": null,
    "lastname": null,
    "username": "MariSa2",
    "email": "maria2@gmail.com"
}
```
