package com.deepmodi.task_manager_api.service;

import com.deepmodi.task_manager_api.exception.ResourceNotFoundException;
import com.deepmodi.task_manager_api.model.Task;
import com.deepmodi.task_manager_api.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TaskServiceImplTest {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    private Task testTask;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        testTask = new Task();
        testTask.setId(1L);
        testTask.setTitle("Test Task");
        testTask.setDescription("Test Description");
        testTask.setCreatedDate(LocalDateTime.now());
        testTask.setDueDate(LocalDate.now().plusDays(1));
        testTask.setCompleted(false);
    }

    @Test
    void createTask(){
        when(taskRepository.save(any(Task.class))).thenReturn(testTask);

        Task createdTask = taskService.createTask(testTask);

        assertNotNull(createdTask);
        assertEquals(testTask.getTitle(),createdTask.getTitle());
        verify(taskRepository,times(1)).save(any(Task.class));
    }

    @Test
    void getTaskById(){
        when(taskRepository.findById(1L)).thenReturn(Optional.of(testTask));

        Task foundTask = taskService.getTaskById(1L);

        assertNotNull(foundTask);
        assertEquals(testTask.getId(),foundTask.getId());
    }

    @Test
    void getTaskByIdNotFound(){
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,() -> taskService.getTaskById(1L));
    }

    @Test
    void getAllTasks(){
        List<Task> taskList = Arrays.asList(testTask, new Task());
        when(taskRepository.findAll()).thenReturn(taskList);

        List<Task> foundTasks = taskService.getAllTasks();

        assertNotNull(foundTasks);
        assertEquals(2, foundTasks.size());
    }
    @Test
    void updateTask(){
        Task updatedTask = new Task();
        updatedTask.setTitle("Updated Title");
        updatedTask.setDescription("Updated Description");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(testTask));
        when(taskRepository.save(any(Task.class))).thenReturn(updatedTask);

        Task result = taskService.updateTask(1L, updatedTask);

        assertNotNull(result);
        assertEquals(updatedTask.getTitle(),result.getTitle());
        assertEquals(updatedTask.getDescription(),result.getDescription());
    }

    @Test
    void deleteTask(){
        when(taskRepository.findById(1L)).thenReturn(Optional.of(testTask));

        taskService.deleteTask(1L);

        verify(taskRepository,times(1)).delete(testTask);
    }

    @Test
    void completeTask(){
        when(taskRepository.findById(1L)).thenReturn(Optional.of(testTask));
        when(taskRepository.save(any(Task.class))).thenReturn(testTask);

        Task completedTask = taskService.completeTask(1L);
        assertTrue(completedTask.isCompleted());
        verify(taskRepository,times(1)).save(testTask);
    }
}

/**
 * This test class covers all the methods in our TaskServiceImpl:
 *
 * It sets up a mock TaskRepository and injects it into the TaskServiceImpl.
 * It creates a sample Task object for testing.
 * It tests each method of the service, verifying that they behave as expected.
 * It includes a test for the "not found" scenario in getTaskById.
 *
 * To run these tests, you can use your IDE's test runner or run mvn test from the command line if you're using Maven.
 * These unit tests help ensure that your service layer is working correctly, even in isolation from the rest of the application. They test the business logic without needing to set up a database or run the entire application.
 */