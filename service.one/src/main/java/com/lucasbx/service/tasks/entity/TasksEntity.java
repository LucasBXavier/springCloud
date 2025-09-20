package com.lucasbx.service.tasks.entity;

import com.lucasbx.service.tasks.dto.TaskRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
@Entity(name = "Task")
public class TasksEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String email;

    private LocalDateTime dueDate;

    private boolean notified;

    public TasksEntity(TaskRequest request) {
        this.title = request.title();
        this.email = request.email();
        this.dueDate = request.dueDate();
        this.notified = request.notified();
    }
}
