package com.priya.actifyusermanagement.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.priya.actifyusermanagement.entity.Task;
import com.priya.actifyusermanagement.entity.User;
import com.priya.actifyusermanagement.service.TaskService;
import com.priya.actifyusermanagement.service.UserService;

@RestController
@RequestMapping("/api/manager")
@PreAuthorize("hasRole('MANAGER')")
public class ManagerController {

    private final UserService userService;
    private final TaskService taskService;

    public ManagerController(
            UserService userService,
            TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/assign-task/{userId}")
    public ResponseEntity<Task> assignTask(
            @PathVariable Long userId,
            @RequestBody Task task) {
        return ResponseEntity.ok(
            taskService.assignTask(userId, task)
        );
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }
}
