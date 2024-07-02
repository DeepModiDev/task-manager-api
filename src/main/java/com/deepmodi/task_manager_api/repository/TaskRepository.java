package com.deepmodi.task_manager_api.repository;

import com.deepmodi.task_manager_api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This repository interface extends JpaRepository,
 * which provides a lot of built-in methods for CRUD operations and more.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
}
