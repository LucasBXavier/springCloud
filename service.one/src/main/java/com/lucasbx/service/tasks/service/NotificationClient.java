package com.lucasbx.service.tasks.service;

import com.lucasbx.service.tasks.dto.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient(name = "service-notification")
public interface NotificationClient {

    @PostMapping("/notification")
    void sendNotification(@RequestBody NotificationRequest notificationRequest);
}
