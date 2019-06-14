# Money_Transfer
A Demo money transfer Rest Service

# Technologies

    JAX-RS API
    H2 in memory database
    Log4j
    Jetty Container (for Test and Demo app)
    Apache HTTP Client

Application starts a jetty server on localhost port 8080

H2 in memory database initialized with some sample user and account data To view

    http://localhost:8080/user/test
    http://localhost:8080/user/qwerty
    http://localhost:8080/account/1
    http://localhost:8080/account/2

# Available Services

| HTTP  | METHODPATH | USAGE |
| ------------- | ------------- | ------------- |
| GET | /user/{userName} | get user by user name |
| GET | /user/all | get all users |
| PUT |	/user/create | create a new user |
| POST | /user/{userId} | update user |
| DELETE | /user/{userId} | remove user |
| GET | /account/{accountId} | get account by accountId |
| GET | /account/all | get all accounts |
| GET | /account/{accountId}/balance | get account balance by accountId |
| PUT |	/account/create | create a new account |
| DELETE | /account/{accountId} | remove account by accountId |
| PUT | /account/{accountId}/withdraw/{amount} |	withdraw money from account |
| PUT | /account/{accountId}/deposit/{amount} |	deposit money to account |
| POST | /transaction | perform transaction between 2 user accounts |


