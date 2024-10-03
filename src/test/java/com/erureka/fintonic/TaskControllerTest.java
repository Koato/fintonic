package com.erureka.fintonic;

import com.erureka.fintonic.domain.models.Task;
import com.erureka.fintonic.domain.ports.in.CreateTaskUseCase;
import com.erureka.fintonic.domain.ports.in.GetAllTaskUseCase;
import com.erureka.fintonic.infrastructure.in.controllers.TaskController;
import com.erureka.fintonic.infrastructure.in.dto.TaskDTO;
import com.erureka.fintonic.infrastructure.in.mapper.TaskMapper;
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
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TaskControllerTest {

    @Mock
    private CreateTaskUseCase createTaskUseCase;

    @Mock
    private GetAllTaskUseCase getAllTaskUseCase;

    @Mock
    private TaskMapper taskMapper;

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
        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":null,\"description\":null,\"dueDate\":null,\"tags\":null}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("title Cannot be null"))
                .andExpect(jsonPath("$.description").value("description Cannot be null"))
                .andExpect(jsonPath("$.dueDate").value("dueDate Cannot be null"))
                .andExpect(jsonPath("$.tags").value("tags Cannot be null"));
    }

    @Test
    void testCreateTask() {
        Task task = new Task();
        task.setTitle("New Task");
        task.setDescription("Task Description");
        task.setDueDate(LocalDate.of(2024, 12, 25));
        task.setTags(Arrays.asList("tag1", "tag2"));

        var taskDTO = new TaskDTO("id", task.getTitle(), task.getDescription(), task.getDueDate(), task.getTags(), false);
        when(taskMapper.toArchitect(any())).thenReturn(task);
        when(createTaskUseCase.createTask(any())).thenReturn(Optional.of(task));
        when(taskMapper.toDocument(task)).thenReturn(taskDTO);

        taskController.createTask(taskDTO);
    }

    @Test
    void testGetAllTasks() {
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
        var taskDTO = new TaskDTO("id", task1.getTitle(), task1.getDescription(), task1.getDueDate(), task1.getTags(), false);
        when(getAllTaskUseCase.getAllTasks()).thenReturn(tasks);
        when(taskMapper.toDocument(any())).thenReturn(taskDTO);

        taskController.getAllTasks();
    }
}