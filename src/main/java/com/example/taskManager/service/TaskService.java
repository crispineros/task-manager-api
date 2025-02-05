package com.example.taskManager.service;

import com.example.taskManager.exception.ResourceNotFoundException;
import com.example.taskManager.model.Task;
import com.example.taskManager.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

@Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Task with ID " + id + " not found"));
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Optional<Task> existingTaskOptional = taskRepository.findById(id);
        if (existingTaskOptional.isPresent()) {
            Task existingTask = existingTaskOptional.get();
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setCompleted(updatedTask.isCompleted());
            return taskRepository.save(existingTask);
        } else {
            throw new RuntimeException("Task not found with id " + id);
        }
    }
    
    public Task patchTask(Long id, Task partialUpdate) {
        Optional<Task> existingTaskOptional = taskRepository.findById(id);
        if (existingTaskOptional.isPresent()) {
            Task existingTask = existingTaskOptional.get();
            if (partialUpdate.getTitle() != null) {
                existingTask.setTitle(partialUpdate.getTitle());
            }
            if (partialUpdate.getDescription() != null) {
                existingTask.setDescription(partialUpdate.getDescription());
            }
            if (partialUpdate.isCompleted() != existingTask.isCompleted()) {
                existingTask.setCompleted(partialUpdate.isCompleted());
            }
            return taskRepository.save(existingTask);
        } else {
            throw new RuntimeException("Task not found with id " + id);
        }
    }
    

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}

