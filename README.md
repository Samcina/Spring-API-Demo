# Spring API Demo

Spring API Demo written for King ICT Academy. <br/>
The application is an API wrapper that uses https://dummyjson.com API as a datasource. <br/>
Since it fetches data directly from another API, no database setup is needed. <br/>
The app runs on http://localhost:8080/. <br/>


# Endpoints

List of all the endpoints that the app uses

## GET /products
Fetches data about all the products <br/>
Example use: http://localhost:8080/products

## GET /products/id
Fetches all the data about a single product based on id <br/>
Example use: http://localhost:8080/products/1

## GET /products/filter
Fetches data about all the products based on category and price <br/>
Parameters: category, pricegt (price greater than), pricelt (price less than) <br/>
Example use: http://localhost:8080/products/filter?category=smartphones&pricegt=300&pricelt=900

## GET /products/search
Fetches data about all the products based on category and price <br/>
Required parameters: q (search query) <br/>
Example use: http://localhost:8080/products/search?q=phone

# Auth endpoints

Uses authetification provided by https://dummyjson.com API. <br/>
Example user info: {"username":  "addisonw", "password":  " addisonwpass"} <br/>
Example admin info: {"username":  "emilys", "password":  "emilyspass"}

## POST /auth/login
Logs in user and returns bearer token for the user <br/>
Required content type: application/json <br/>
Required body: username, password <br/>
Example use: http://localhost:8080/auth/login <br/>
Example Body: {"username":  "emilys", "password":  "emilyspass"}

## GET /products/auth/user
Fetches data about all the products to users with a provided bearer token <br/>
Required header: Authorization (with provided bearer token) <br/>
Example use: http://localhost:8080/products/auth/user

## GET /products/auth/admin
Fetches data about all the products to admins with a provided bearer token
Required header: Authorization (with provided bearer token)
Example use: http://localhost:8080/products/auth/admin
