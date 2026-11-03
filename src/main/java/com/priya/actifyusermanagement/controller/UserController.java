package com.priya.actifyusermanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.priya.actifyusermanagement.entity.User;
import com.priya.actifyusermanagement.entity.Task;
import com.priya.actifyusermanagement.service.UserService;
import com.priya.actifyusermanagement.service.TaskService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final TaskService taskService;

    public UserController(UserService userService,
                          TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getProfile(
            Authentication authentication) {

        User user = userService.getUserByEmail(
            authentication.getName()
        );

        return ResponseEntity.ok(user);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getMyTasks(
            Authentication authentication) {

        User user = userService.getUserByEmail(
            authentication.getName()
        );

        return ResponseEntity.ok(
            taskService.getUserTasks(user.getId())
        );
    }
}
