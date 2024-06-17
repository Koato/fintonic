package com.erureka.fintonic;

import com.erureka.fintonic.application.service.TaskService;
import com.erureka.fintonic.domain.model.Task;
import com.erureka.fintonic.infrastructure.controller.TaskController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    void testCreateTaskWithInvalidData() throws Exception {
        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":null,\"description\":null,\"dueDate\":null,\"tags\":null}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Cannot be null"))
                .andExpect(jsonPath("$.description").value("Cannot be null"))
                .andExpect(jsonPath("$.dueDate").value("Cannot be null"))
                .andExpect(jsonPath("$.tags").value("Cannot be null"));
    }

    @Test
    void testCreateTask() throws Exception {
        Task task = new Task();
        task.setTitle("New Task");
        task.setDescription("Task Description");
        task.setDueDate(LocalDate.of(2024, 12, 25));
        task.setTags(Arrays.asList("tag1", "tag2"));

        when(taskService.createTask(task)).thenReturn(task);

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"New Task\",\"description\":\"Task Description\",\"dueDate\":\"25/12/2024\",\"tags\":[\"tag1\",\"tag2\"]}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("New Task"))
                .andExpect(jsonPath("$.description").value("Task Description"))
                .andExpect(jsonPath("$.dueDate").value("25/12/2024"))
                .andExpect(jsonPath("$.tags", hasSize(2)))
                .andExpect(jsonPath("$.tags[0]").value("tag1"))
                .andExpect(jsonPath("$.tags[1]").value("tag2"));;
    }

    @Test
    void testGetAllTasks() throws Exception {
        Task task1 = new Task();
        task1.setTitle("Task 1");
        task1.setDescription("Description 1");
        task1.setDueDate(LocalDate.of(2024, 12, 25));
        task1.setTags(Arrays.asList("tag1", "tag2"));

        Task task2 = new Task();
        task2.setTitle("Task 2");
        task2.setDescription("Description 2");
        task2.setDueDate(LocalDate.of(2024, 12, 26));
        task2.setTags(Arrays.asList("tag3", "tag4"));

        List<Task> tasks = Arrays.asList(task1, task2);

        when(taskService.getAllTasks()).thenReturn(tasks);

        mockMvc.perform(get("/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title").value("Task 1"))
                .andExpect(jsonPath("$[0].description").value("Description 1"))
                .andExpect(jsonPath("$[0].dueDate").value("25/12/2024"))
                .andExpect(jsonPath("$[0].tags", hasSize(2)))
                .andExpect(jsonPath("$[0].tags[0]").value("tag1"))
                .andExpect(jsonPath("$[0].tags[1]").value("tag2"))
                .andExpect(jsonPath("$[1].title").value("Task 2"))
                .andExpect(jsonPath("$[1].description").value("Description 2"))
                .andExpect(jsonPath("$[1].dueDate").value("26/12/2024"))
                .andExpect(jsonPath("$[1].tags", hasSize(2)))
                .andExpect(jsonPath("$[1].tags[0]").value("tag3"))
                .andExpect(jsonPath("$[1].tags[1]").value("tag4"));
    }
}