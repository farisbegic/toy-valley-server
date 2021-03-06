# Overview
ToyValley is a web application that aims to recycle as many toys as possible by allowing parents to trade toys that their children are no longer using. Each person can create a profile and submit all of their toys for the exchange. Other people can look at the toys and make swap requests using their profile. Users will find easy filters such as most popular categories and most popular cities on the application. 

On this repository, the server side code of the application can be found. 

# Running the project

1. Have an IDE installed. (such as Intellij).

2. Clone the repository:
```
git clone https://github.com/farisbegic/toy-valley-server
```

3. Open the pom.xml file in the IDE and confirm to open it as a project.

4. Create a postgresql database called toy_valley.

5. Click the run/debug icon or Shift+F10 on the keyboard.

The application is running in your browser at `localhost:8080`.
The DBMS used is Postgresql running on port 5432. The name of the database is toy_valley. 

# The project includes the following entities

### User:
- id
- name
- surname
- phone
- address
- email
- password
- active
- city (foreign key -> references City)

### Toy:
- id
- name
- description
- brand
- gender
- condition
- age
- date_purchased
- date_posted
- active
- user (foreign key -> references User)

### City:
- id
- name

### Category:
- id
- name
- description

### ExchangeRequest
- id
- toy_offered (foreign key -> references Toy)
- toy_requested (foreign key -> references Toy)
- active
- message
- request_date
- accept_date

### Preference:
- id
- user (foreign key -> references User)
- category (foreign key -> references Category)

### ToyCategory:
- id
- toy (foreign key -> references Toy)
- category (foreign key -> references Category)

### ToyImage:
- id
- toy (foreign key -> references Toy)
- path

# The project includes the following environment variables:

## PORT -  to tell web server what port to listen on
## DB_PORT - database port
## DB_NAME - database name
## DB_USERNAME - database username (credentials)
## DB_PASSWORD - database password (credentials)
## FRONTEND_URL - to generate base url for connection with frontend
## SECRET_KEY - for JWT token creation
## TOKEN_DURATION_MINUTES - to set token duration

# The project includes the following endpoints
* Post mapping `/authenticate` - for user authentication and jwt token obtaining
* Get mapping `/categories` - for getting all categories
* Get mapping `/categories/{id}` - for getting a specific category
* Get mapping `/categories/search/{categoryName}` - for getting category/categories based on name
* Post mapping `/categories` - for creating a new category
* Put mapping `/categories/{id}` - for updating an existing category
* Delete mapping `/categories/{id}` - for deleting a specific category 
* Get mapping `/cities` - for getting all the cities
* Get mapping `/cities/{id}` - for getting a specific city
* Delete mapping `/cities/{id}` - for deleting a specific city
* Post mapping `/cities` - for creating a new city
* Get mapping `/toys` - for getting all toys
* Get mapping `/toys/{id}` - for getting a specific toy
* Get mapping `/toys/category/{categoryId}` - for getting toys based on category
* Get mapping `/toys/city/{cityId}` - for getting toys based on city
* Get mapping `/toys/gender/{genderId}` - for getting toys based on gender
* Get mapping `/toys/condition/{conditioId}` - for getting toys based on condition
* Get mapping `/toys/user/{userId}` - for getting all user's toys
* Get mapping `/toys/search/{toyName}` - for getting toys based on name
* Post mapping `/toys/{userId}` - for user creating a toy
* Put mapping `/toys/{id}` - for updating a specific toy
* Delete mapping `/toys/{id}` - for deleting a specific toy
* Post mapping `/toy-exchange` - for creating a toy exchange request
* Get mapping `/users` - for getting all users
* Get mapping `/users/{id}` - for getting a specific user
* Post mapping `/users` - for creating a user
* Put mapping `/users/{id}` - for updating a specific user
* Delete mapping `/users/{id}` - for deleting a specific user
* Get mapping `/users/top-traders` - for getting top trader users
* Post mapping `/users/login` - for returnin status (logged in/not logged in)
