package com.deepmodi.task_manager_api.service;

import com.deepmodi.task_manager_api.model.Task;

import java.util.List;

public interface TaskService {
    Task createTask(Task task);
    Task getTaskById(Long id);
    List<Task> getAllTasks();
    Task updateTask(Long id,Task taskDetails);
    void deleteTask(Long id);
    Task completeTask(Long id);
}
