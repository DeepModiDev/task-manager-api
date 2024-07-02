package com.deepmodi.task_manager_api.controller;

import com.deepmodi.task_manager_api.model.Task;
import com.deepmodi.task_manager_api.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Task Management", description = "APIs for managing tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping
    @Operation(summary = "Create a new Task", description = "Creates a new task with the provided details")
    @ApiResponse(responseCode = "201", description = "Task Created successfully")
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task){
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a task by ID", description = "Retrieves a task by its ID")
    @ApiResponse(responseCode = "200", description = "Task found")
    @ApiResponse(responseCode = "404", description = "Task not found")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id){
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @GetMapping
    @Operation(summary = "Get All Tasks", description = "Get all the user created tasks")
    @ApiResponse(responseCode = "200", description = "Get all list of tasks")
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update task", description = "Update the specific task with the help of the ID")
    @ApiResponse(responseCode = "200", description = "Get updated task in the response")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody Task taskDetails){
        Task updatedTask = taskService.updateTask(id, taskDetails);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete task", description = "Delete the task with the help of the id")
    @ApiResponse(responseCode = "204", description = "No content will be returned in the response of the successful deletion.")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/complete")
    @Operation(summary = "Complete task", description = "Mark the given task is completed or not. take id.")
    @ApiResponse(responseCode = "200", description = "Return the updated task with the completed flag.")
    public ResponseEntity<Task> completeTask(@PathVariable Long id){
        Task completedTask = taskService.completeTask(id);
        return ResponseEntity.ok(completedTask);
    }
}

/**
 * This controller implements all the REST endpoints we specified earlier:
 * POST /api/tasks - Create a new task
 * GET /api/tasks/{id} - Retrieve a specific task
 * GET /api/tasks - Retrieve all tasks
 * PUT /api/tasks/{id} - Update a task
 * DELETE /api/tasks/{id} - Delete a task
 * PATCH /api/tasks/{id}/complete - Mark a task as completed
 */