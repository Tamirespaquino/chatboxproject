# ChatBox Project

Project developed to learn more about Microservices and Java programming language

## Diagram

![ChatBoxDiagram](/img/chatImg.png )

## Microservices

* *Producer*: send message to RabbitMQ

* *Consumer*: receive message from RabbitMQ and save at MongoDB

* *Service*: connect to MongoDB and search users/messages

## Request

### POST:
* **/send-message**: Send message to RabbitMQ

```bash
curl --location --request POST 'http://localhost:8080/send-message' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"Teobaldo",
    "message": "Bom dia, Fifi! Tudo bem com você?"
}'
```

Response:
```bash
Status 200 OK

Message has been sent successfully!
```

### GET:
* **/messages**: Find all messages
```bash
curl --location --request GET 'http://localhost:9091/messages'
```

Response:
```bash
Status 200 OK

[
    {
        "id": "62ed65ceb144bf6854d14f81",
        "name": "Mel",
        "message": "Bom dia, pessoal!",
        "date": null
    },
    {
        "id": "62ed65dfb144bf6854d14f82",
        "name": "Teobaldo",
        "message": "Bom dia, Mel. Como está a Fifi?",
        "date": null
    }
]
```

* **/messages/{id}**: Find message by Id

```bash
curl --location --request GET 'http://localhost:9091/messages/62ed65ceb144bf6854d14f81'
```

Response:
```bash
Status 200 OK

{
    "id": "62ed65ceb144bf6854d14f81",
    "name": "Mel",
    "message": "Bom dia, pessoal!",
    "date": null
}
```
