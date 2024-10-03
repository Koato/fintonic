package com.erureka.fintonic;

import com.erureka.fintonic.application.usecases.CreateTaskUseCaseImpl;
import com.erureka.fintonic.domain.models.Task;
import com.erureka.fintonic.domain.ports.out.TaskRepositoryPort;
import com.erureka.fintonic.infrastructure.in.dto.TaskDTO;
import com.erureka.fintonic.infrastructure.in.mapper.TaskMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.time.LocalDate;
import java.util.Arrays;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreateTaskUseCaseTest {
    @Mock
    private TaskRepositoryPort taskRepositoryPort;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private CreateTaskUseCaseImpl createTaskUseCase;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(createTaskUseCase).build();
    }

    @Test
    void testCreateData() {
        var task = new Task();
        task.setDueDate(LocalDate.now());
        task.setDescription("description");
        task.setTitle("title");
        task.setTags(Arrays.asList("tag1", "tag2"));
        task.setCompleted(true);

        var taskDTO = new TaskDTO("id", task.getTitle(), task.getDescription(), task.getDueDate(), task.getTags(), false);

        when(taskMapper.toDocument(any())).thenReturn(taskDTO);
        when(taskRepositoryPort.create(any())).thenReturn(taskDTO);
        when(taskMapper.toArchitect(any())).thenReturn(task);

        createTaskUseCase.createTask(task);
    }
}
