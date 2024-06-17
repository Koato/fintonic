package com.erureka.fintonic;

import com.erureka.fintonic.application.service.TaskService;
import com.erureka.fintonic.domain.model.Task;
import com.erureka.fintonic.domain.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTask() {
        Task task = new Task();
        task.setTitle("New Task");
        task.setDescription("Task Description");
        task.setDueDate(LocalDate.of(2024, 12, 25));

        when(taskRepository.save(task)).thenReturn(task);

        Task createdTask = taskService.createTask(task);

        assertEquals(task, createdTask);
    }

    @Test
    void testGetAllTasks() {
        Task task1 = new Task();
        task1.setTitle("Task 1");
        task1.setDescription("Description 1");
        task1.setDueDate(LocalDate.of(2024, 12, 25));

        Task task2 = new Task();
        task2.setTitle("Task 2");
        task2.setDescription("Description 2");
        task2.setDueDate(LocalDate.of(2024, 12, 26));

        List<Task> tasks = Arrays.asList(task1, task2);

        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> allTasks = taskService.getAllTasks();

        assertEquals(2, allTasks.size());
        assertEquals("Task 1", allTasks.get(0).getTitle());
        assertEquals("Task 2", allTasks.get(1).getTitle());
    }
}