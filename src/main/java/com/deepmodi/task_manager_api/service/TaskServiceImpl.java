package com.deepmodi.task_manager_api.service;

import com.deepmodi.task_manager_api.exception.ResourceNotFoundException;
import com.deepmodi.task_manager_api.model.Task;
import com.deepmodi.task_manager_api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }
    @Override
    public Task createTask(Task task) {
        if (task.getCreatedDate() == null) {
            task.setCreatedDate(LocalDateTime.now());
        }
        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Task not found with id " + id));
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task updateTask(Long id, Task taskDetails) {
        Task task = getTaskById(id);
        task.setTitle(taskDetails.getTitle());
        task.setDescription(task.getDescription());
        task.setDueDate(task.getDueDate());
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }

    @Override
    public Task completeTask(Long id) {
        Task task = getTaskById(id);
        task.setCompleted(true);
        return taskRepository.save(task);
    }
}
