package com.example.taskManager.controller;

import com.example.taskManager.model.Task;
import com.example.taskManager.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Tasks", description = "Endpoints for managing tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @Operation(summary = "Get all tasks", description = "Retrieves a list of all tasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a task by ID", description = "Retrieves a task by its ID")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new task", description = "Adds a new task to the database")
    public Task createTask(@RequestBody @Valid Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a task", description = "Updates an existing task")
    public Task updateTask(@PathVariable Long id, @RequestBody @Valid Task task) {
        return taskService.updateTask(id, task);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partially update a task", description = "Updates specific fields of a task")
    public Task patchTask(@PathVariable Long id, @RequestBody @Valid Task task) {
        return taskService.patchTask(id, task);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a task", description = "Removes a task from the database")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
