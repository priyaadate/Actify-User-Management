package com.priya.actifyusermanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.priya.actifyusermanagement.entity.Task;
import com.priya.actifyusermanagement.entity.User;
import com.priya.actifyusermanagement.repository.TaskRepository;
import com.priya.actifyusermanagement.repository.UserRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository,
                       UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Task assignTask(Long userId, Task task) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                    new RuntimeException("User not found"));

        task.setAssignedUser(user);

        return taskRepository.save(task);
    }

    public List<Task> getUserTasks(Long userId) {
        return taskRepository.findByAssignedUserId(userId);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
