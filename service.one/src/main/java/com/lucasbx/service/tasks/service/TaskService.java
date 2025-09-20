package com.lucasbx.service.tasks.service;

import com.lucasbx.service.tasks.dto.NotificationRequest;
import com.lucasbx.service.tasks.entity.TasksEntity;
import com.lucasbx.service.tasks.repository.TasksRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TasksRepository tasksRepository;
    private final NotificationClient notificationClient;


    public void sendNotificationOnDueTasks() {
        LocalDateTime deadline = LocalDateTime.now().plusDays(1);
        List<TasksEntity> tasks = tasksRepository.findDueTasks(deadline);

        for(TasksEntity task : tasks) {
            NotificationRequest request = new NotificationRequest("A tarefa " + task.getTitle() + " irá vencer!", task.getEmail());
            notificationClient.sendNotification(request);
            task.setNotified(true);
            tasksRepository.save(task);
        }
    }
}
