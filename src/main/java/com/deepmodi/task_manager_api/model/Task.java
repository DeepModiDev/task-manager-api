package com.deepmodi.task_manager_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required.")
    private String title;

    private String description;

    private LocalDateTime createdDate;

    private LocalDate dueDate;

    private boolean completed;

    @PrePersist
    protected void onCreate(){
        createdDate = LocalDateTime.now();
    }
}

/**
 * Let's break down this entity:
 * We use JPA annotations (@Entity, @Table, @Id, @GeneratedValue) to define how this entity maps to a database table.
 * @Data is a Lombok annotation that automatically generates getters, setters, toString, equals, and hashCode methods.
 * We use validation annotations like @NotBlank and @NotNull to ensure data integrity.
 * The @PrePersist annotation on the onCreate() method ensures that the createdDate is automatically set when a new Task is created.
 */
