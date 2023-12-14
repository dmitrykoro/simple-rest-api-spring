# Simple REST API for adding a data to the database.
In  this application, there's imaginary paper submission system, and this is the example of APIs for paper-related 
operations. Can be further enhanced and used in a REST application. 

Requirements:
* PostgreSQL 15
* Apache Maven 3.9.5
* Java 20.02
* curl (for testing)

### How to run

* Create the database for the project and update credentials in `src/main/resources/application.properties`
* Resolve dependencies:
```shell
mvn dependency:resolve
```

* Execute `ServerApplication` class
* Then, create a new account: 
```shell
curl --location --request POST 'http://127.0.0.1:8080/api/v1/accounts/create' \
--header 'Content-Type: application/json' \
--data '{"id":"account_id"}'
```

* Then it's possible to perform various operations on paper, such as: 

Create: 
```shell
curl --location --request POST 'http://127.0.0.1:8080/api/v1/papers/create' \
--header 'Content-Type: application/json' \
--data '{
    "title": "Paper 0",
    "abstract_summary": "summary",
    "filepath": "path/to/file",
    "author_ids": [
        "author_id"
    ]
}'
```

Get all: 
```shell
curl --location --request GET 'http://127.0.0.1:8080/api/v1/papers/all' \
--header 'Content-Type: application/json'
```

Get with filter:
```shell
curl --location --request GET 'http://127.0.0.1:8080/api/v1/papers/author/author_id' \
--header 'Content-Type: application/json'
```

Update:
```shell
curl --location --request PUT 'http://127.0.0.1:8080/api/v1/papers/update/93a413c9-afa6-462f-91d3-ffad5ed8316c' \
--header 'Content-Type: application/json' \
--data '{
    "title": "Paper 1 updated",
    "abstract_summary": "summary fixed",
    "filepath": "path/to/file",
    "author_ids": [
        "author_id"
    ]
}'
```

Delete:
```shell
curl --location --request DELETE 'http://127.0.0.1:8080/api/v1/papers/delete/93a413c9-afa6-462f-91d3-ffad5ed8316c' \
--header 'Content-Type: application/json'
```



