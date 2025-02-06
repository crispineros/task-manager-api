package com.example.taskManager.service;

import com.example.taskManager.exception.ResourceNotFoundException;
import com.example.taskManager.model.Task;
import com.example.taskManager.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task("Test Task", "Test Description", false);
    }

    @Test
    void getAllTasks_ShouldReturnListOfTasks() {
        when(taskRepository.findAll()).thenReturn(Arrays.asList(task));
        List<Task> tasks = taskService.getAllTasks();
        assertEquals(1, tasks.size());
        assertEquals("Test Task", tasks.get(0).getTitle());
    }

    @Test
    void getTaskById_ShouldReturnTask_WhenTaskExists() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        Task foundTask = taskService.getTaskById(1L);
        assertEquals("Test Task", foundTask.getTitle());
    }

    @Test
    void getTaskById_ShouldThrowException_WhenTaskNotFound() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> taskService.getTaskById(1L));
    }

    @Test
    void createTask_ShouldSaveTask() {
        when(taskRepository.save(task)).thenReturn(task);
        Task savedTask = taskService.createTask(task);
        assertNotNull(savedTask);
        assertEquals("Test Task", savedTask.getTitle());
    }

    @Test
    void updateTask_ShouldUpdateTask_WhenTaskExists() {
        Task updatedTask = new Task("Updated Task", "Updated Description", true);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(updatedTask);
        Task result = taskService.updateTask(1L, updatedTask);
        assertEquals("Updated Task", result.getTitle());
        assertTrue(result.isCompleted());
    }

    @Test
    void updateTask_ShouldThrowException_WhenTaskNotFound() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());
        Task updatedTask = new Task("Updated Task", "Updated Description", true);
        assertThrows(RuntimeException.class, () -> taskService.updateTask(1L, updatedTask));
    }

    @Test
    void patchTask_ShouldUpdatePartialFields_WhenTaskExists() {
        Task partialUpdate = new Task(null, "Patched Description", true);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        Task result = taskService.patchTask(1L, partialUpdate);
        assertEquals("Patched Description", result.getDescription());
        assertTrue(result.isCompleted());
    }

    @Test
    void patchTask_ShouldThrowException_WhenTaskNotFound() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());
        Task partialUpdate = new Task(null, "Patched Description", true);
        assertThrows(RuntimeException.class, () -> taskService.patchTask(1L, partialUpdate));
    }

    @Test
    void deleteTask_ShouldDeleteTask() {
        doNothing().when(taskRepository).deleteById(1L);
        assertDoesNotThrow(() -> taskService.deleteTask(1L));
        verify(taskRepository, times(1)).deleteById(1L);
    }
}


