package com.lucasbx.service.tasks.service.schedule;

import com.lucasbx.service.tasks.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskNotificationSchedule {

    private final TaskService service;

    @Scheduled(fixedRate = 60) // Runs every 60 seconds
    public void checkAndNotifyTasks() {
        service.sendNotificationOnDueTasks();
    }
}
