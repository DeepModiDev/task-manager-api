package com.deepmodi.task_manager_api.Controller;

import com.deepmodi.task_manager_api.model.Task;
import com.deepmodi.task_manager_api.repository.TaskRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        taskRepository.deleteAll();
    }

    @Test
    void createTask() throws Exception{
        Task task = new Task();
        task.setTitle("Integration Test Task");
        task.setDescription("This is an integration test");
        task.setDueDate(LocalDate.now().plusDays(1));

        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title",is(task.getTitle())))
                .andExpect(jsonPath("$.description",is(task.getDescription())))
                .andExpect(jsonPath("$.id",notNullValue()));
    }

    @Test
    void getAllTasks() throws Exception{
        createTestTask("Task 1");
        createTestTask("Task 2");

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("Task 1")))
                .andExpect(jsonPath("$[1].title",is("Task 2")));
    }

    @Test
    void getTaskById() throws Exception{
        Task task = createTestTask("Get By ID Task");

        mockMvc.perform(get("/api/tasks/{id}", task.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(task.getTitle())))
                .andExpect(jsonPath("$.id",is(task.getId().intValue())));
    }

    @Test
    void updateTask() throws Exception{
        Task task = createTestTask("Original Task");

        Task updatedTask = new Task();
        updatedTask.setTitle("Updated Task");
        updatedTask.setDescription("Updated Description");
        updatedTask.setDueDate(LocalDate.now().plusDays(2));

        mockMvc.perform(put("/api/tasks/{id}", task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedTask)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title",is("Updated Task")))
                .andExpect(jsonPath("$.description", is("Updated Description")));
    }

    @Test
    void deleteTask() throws Exception{
        Task task = createTestTask("Task to Delete");

        mockMvc.perform(delete("/api/tasks/{id}", task.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/tasks/{id}",task.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void completeTask() throws Exception{
        Task task = createTestTask("Task to Complete");

        mockMvc.perform(patch("/api/tasks/{id}/complete", task.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.completed", is(true)));
    }

    private Task createTestTask(String title){
        Task task = new Task();
        task.setTitle(title);
        task.setDescription("Test Description");
        task.setDueDate(LocalDate.now().plusDays(1));
        return taskRepository.save(task);
    }
}

/**
 *
 It uses @SpringBootTest to load the entire application context.
 It uses @AutoConfigureMockMvc to auto-configure MockMvc, which we use to perform requests to our API.
 Before each test, it clears the database to ensure a clean state.
 It tests all CRUD operations and the complete task functionality.
 It uses MockMvc to perform HTTP requests and verify the responses.
 */