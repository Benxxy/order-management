# Order Management

Order Management is an application used for purchase purpose.


## Requirements

For building and running the application you need:

- [JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven 3](https://maven.apache.org)

# Security

Integration with Spring Security and add other filter for jwt token process.

The secret key is stored in `application.yaml`.

# Database

It uses a H2 in memory database (for now), can be changed easily in the `application.yaml` for any other database.

# Getting started

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.project.order.management.Application` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

To test that it works, open a browser tab at http://127.0.0.1:8081/products  
Alternatively, you can run

    curl http://127:0.0.1:8081/products

At starting the application `init.sql` will be executed populating the database with sample data to test the application.

### User:
```
{
    "username":"username",
    "password":"Password29"
}
```

### Orders:

``` 
[
    {
        "orderId": 1,
        "orderNumber": "7f06a9aa-b99f-4482-884c-ec9ef1c5c9df",
        "orderDate": "2021-05-13",
        "orderDetailsDTO": [
            {
                "productName": "Iphone 12 PRO MAX",
                "quantity": 1,
                "price": 989.99,
                "totalPrice": 989.99
            }
        ]
    },
    {
        "orderId": 2,
        "orderNumber": "ca0604be-035d-4e14-8655-3da9426f0ab0",
        "orderDate": "2021-05-13",
        "orderDetailsDTO": [
            {
                "productName": "Iphone 12 PRO MAX",
                "quantity": 3,
                "price": 989.99,
                "totalPrice": 2969.97
            }
        ]
    },
    {
        "orderId": 3,
        "orderNumber": "4b8a9312-3331-416f-9f8d-e2adf1ed8a7e",
        "orderDate": "2021-05-13",
        "orderDetailsDTO": [
            {
                "productName": "Lenovo IDEAPAD",
                "quantity": 2,
                "price": 1057.89,
                "totalPrice": 2115.78
            }
        ]
    },
    {
        "orderId": 4,
        "orderNumber": "6ed4fcda-f11a-4c27-a8bd-4e5eaf75217a",
        "orderDate": "2021-05-13",
        "orderDetailsDTO": [
            {
                "productName": "Lenovo IDEAPAD",
                "quantity": 5,
                "price": 1057.89,
                "totalPrice": 5289.45
            }
        ]
    },
    {
        "orderId": 5,
        "orderNumber": "63303297-f70d-4949-abf8-d9ed01b43011",
        "orderDate": "2021-05-13",
        "orderDetailsDTO": [
            {
                "productName": "Iphone 12 PRO MAX",
                "quantity": 9,
                "price": 989.99,
                "totalPrice": 8909.91
            }
        ]
    }
]
 ```

### Products:

```
[
    {
        "id": 1,
        "name": "Iphone 12 PRO MAX",
        "description": "Mobilephones",
        "price": 989.99
    },
    {
        "id": 2,
        "name": "Lenovo IDEAPAD",
        "description": "Laptop",
        "price": 1057.89
    }
]
```
# Run test

The repository contains a test cases to cover initialization of DB data in purpose of testing the application.

    mvn test

# Explore Api Documentation

#### Swagger Api Documentation

swagger.json: `http://127.0.0.1:8081/v2/api-docs`

swagger-ui: `http://127.0.0.1:8081/swagger-ui.html`

#### Postman Collection (Recomendation)
Use Postman collection to explore and test application. Collection is configured with global variables for easier usage.
Postman Collection can be found under `postman-collection` directory.
