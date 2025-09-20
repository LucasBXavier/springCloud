package com.lucasbx.service.tasks.repository;

import com.lucasbx.service.tasks.entity.TasksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<TasksEntity, Long> {

    @Query("SELECT t FROM Task t WHERE t.dueDate <= :day AND t.notified = false")
    List<TasksEntity> findDueTasks(LocalDateTime day);
}
