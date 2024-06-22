# Spring API Demo

Spring API Demo written for King ICT Academy.
The application is an API wrapper that uses https://dummyjson.com API as a datasource.
Since it fetches data directly from another API, no database setup is needed.
The app runs on http://localhost:8080/.


# Endpoints

List of all the endpoints that the app uses

## GET /products
Fetches data about all the products
Example use: http://localhost:8080/products

## GET /products/id
Fetches all the data about a single product based on id
Example use: http://localhost:8080/products/1

## GET /products/filter
Fetches data about all the products based on category and price
Parameters: category, pricegt (price greater than), pricelt (price less than)
Example use: http://localhost:8080/products/filter?category=smartphones&pricegt=300&pricelt=900

## GET /products/search
Fetches data about all the products based on category and price
Required parameters: q (search query)
Example use: http://localhost:8080/products/search?q=phone

# Auth endpoints

Uses authetification provided by https://dummyjson.com API.
Example user info: {"username":  "addisonw", "password":  " addisonwpass"}
Example admin info: {"username":  "emilys", "password":  "emilyspass"}

## POST /auth/login
Logs in user and returns bearer token for the user
Required content type: application/json
Required body: username, password
Example use: http://localhost:8080/auth/login
Example Body: {"username":  "emilys", "password":  "emilyspass"}

## GET /products/auth/user
Fetches data about all the products to users with a provided bearer token
Required header: Authorization (with provided bearer token)
Example use: http://localhost:8080/products/auth/user

## GET /products/auth/admin
Fetches data about all the products to admins with a provided bearer token
Required header: Authorization (with provided bearer token)
Example use: http://localhost:8080/products/auth/admin
