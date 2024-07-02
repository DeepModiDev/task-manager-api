# Task Management API 📝

## Objective 🎯

Build a RESTful API for a simple task management system using Spring Boot.

## Features to Implement 🌟
-  Create a new task
-  Retrieve a single task by ID
-  Retrieve all tasks
-  Update an existing task
-  Delete a task
-  Mark a task as completed

## Data Model 🗂️

### Task Entity

- **id** (Long)
- **title** (String)
- **description** (String)
- **createdDate** (LocalDateTime)
- **dueDate** (LocalDate)
- **completed** (Boolean)

## API Endpoints 🚀

-  **POST** `/api/tasks` - Create a new task
-  **GET** `/api/tasks/{id}` - Retrieve a specific task
-  **GET** `/api/tasks` - Retrieve all tasks
-  **PUT** `/api/tasks/{id}` - Update a task
-  **DELETE** `/api/tasks/{id}` - Delete a task
-  **PATCH** `/api/tasks/{id}/complete` - Mark a task as completed

## Implementation Steps 🔧

1. Set up a new Spring Boot project using Spring Initializer
2. Create the Task entity class
3. Implement a TaskRepository interface extending JpaRepository
4. Create a TaskService class to handle business logic
5. Implement a TaskController class to define API endpoints
6. Add proper exception handling
7. Implement basic validation for task creation and updates
8. Write unit tests for the service layer
9. Write integration tests for the API endpoints

## Expected Outcome 🎉

A functioning RESTful API that allows clients to perform CRUD operations on tasks.

## Testing 🧪

-  Use Postman or curl to test API endpoints manually
-  Verify all CRUD operations work as expected
-  Check appropriate status codes and error messages are returned

## Bonus Challenges 🎁

- [ ] Implement pagination for the "get all tasks" endpoint
- [ ] Add filtering options (e.g., by completion status, due date)
- [ ] Implement basic authentication using Spring Security
- [ ] Add Swagger documentation to your API

## Learning Objectives 📚

- Setting up a Spring Boot application
- Creating RESTful API endpoints
- Working with JPA for data persistence
- Implementing service layer logic
- Handling exceptions in a Spring Boot application
- Writing unit and integration tests
