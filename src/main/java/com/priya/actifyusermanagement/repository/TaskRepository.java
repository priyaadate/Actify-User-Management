package com.priya.actifyusermanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.priya.actifyusermanagement.entity.Task;

public interface TaskRepository
        extends JpaRepository<Task, Long> {

    List<Task> findByAssignedUserId(Long userId);
}
