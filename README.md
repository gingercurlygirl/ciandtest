# CI and Testing

## Application description

An API service has been created that acts as a todo-list. Users can add tasks to the todo list. The tasks can
be checked for completion.

## How to run?
1. Set environment variables:

| Environment variable | Description                                     |
|----------------------|-------------------------------------------------|
| DB_USERNAME          | Username of your local mysql server             |
| DB_PASSWORD          | Password for your mysql user                    |
| DB_DATABASE          | URI to mysql database used by this app          |
| DB_TEST_DATABASE     | URI to mysql database used only for app testing |

2. Create a database specified in DB_DATABASE environment variable
3. Run `src/Main.java`

## How to test?
1. Make sure "How to run?" section works as intended first.
2. Add one more environment variable:

| Environment variable | Description                                      |
|----------------------|--------------------------------------------------|
| DB_TEST_DATABASE     | URI to your mysql database used only for testing |

3. Create a database specified in DB_TEST_DATABASE environment variable
4. Run tests: `mvn test` 

## REST API Endpoints

### Response datatypes examples

#### UserDTO:
```aiignore
{
    "id": 1,
    "username": "ivana",
    "age": 23,
    "todos": [
        "Get to the bank",
        "Dinner anniversary"
    ]
}
```

#### Todo:
```aiignore
{
    "id": 1,
    "task": "Get to the bank",
    "create_date": "2025-03-16",
    "completed": false
}
```

### User Endpoints

| Method | Endpoint | Description | Request Body | Response |
|--------|----------|-------------|--------------|----------|
| POST | `/users` | Create a new user | `{"username": "string", "age": number}` | `UserDTO` |
| GET | `/users` | Get all users | - | `List<UserDTO>` |
| GET | `/users/{userId}` | Get user by ID | - | `UserDTO` |
| PUT | `/users/{userId}` | Update user | `{"username": "string", "age": number}` | `UserDTO` |
| DELETE | `/users/{userId}` | Delete user | - | `UserDTO` |
| POST | `/users/{userId}` | Create todo for user | `{"task": "string", "created": "YYYY-MM-DD", "completed": boolean}` | `Todo` |

### Todo Endpoints

| Method | Endpoint | Description | Request Body | Response |
|--------|----------|-------------|--------------|----------|
| POST | `/todos/user/{userId}` | Create todo for user | `{"task": "string", "created": "YYYY-MM-DD", "completed": boolean}` | `Todo` |
| GET | `/todos` | Get all todos | - | `List<Todo>` |
| GET | `/todos/{userId}` | Get todos for user | - | `List<Todo>` |
| PUT | `/todos/{todoId}` | Update todo | `{"task": "string", "completed": boolean}` | `Todo` |
| DELETE | `/todos/{todoId}` | Delete todo | - | `Todo` |


## Testing

Strategies used for testing are unit, integration, and component tests. Every test contains useful comments.
