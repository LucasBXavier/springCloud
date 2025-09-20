package com.lucasbx.service.tasks.controller;

import com.lucasbx.service.tasks.dto.TaskRequest;
import com.lucasbx.service.tasks.entity.TasksEntity;
import com.lucasbx.service.tasks.repository.TasksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TasksController {

    private final TasksRepository tasksRepository;

    @PostMapping
    public ResponseEntity<TasksEntity> createTask(@RequestBody TaskRequest request) {
        TasksEntity entity = new TasksEntity(request);
        return ResponseEntity.ok(tasksRepository.save(entity));
    }
}
