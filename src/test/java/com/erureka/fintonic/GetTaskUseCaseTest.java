package com.erureka.fintonic;

import com.erureka.fintonic.application.usecases.GetAllTaskUseCaseImpl;
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
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class GetTaskUseCaseTest {

    @Mock
    private TaskRepositoryPort taskRepositoryPort;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private GetAllTaskUseCaseImpl getAllTaskUseCase;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(getAllTaskUseCase).build();
    }

    @Test
    void testCreateData() {
        var task = new Task();
        task.setDueDate(LocalDate.now());
        task.setDescription("description");
        task.setTitle("title");
        task.setTags(Arrays.asList("tag1", "tag2"));
        task.setCompleted(true);
        var listTasks = List.of(task);

        var taskDTO = new TaskDTO("id", task.getTitle(), task.getDescription(), task.getDueDate(), task.getTags(), false);
        var listaDTO = List.of(taskDTO);
        when(taskRepositoryPort.findAll()).thenReturn(listaDTO);
        when(taskMapper.toTasks(any())).thenReturn(listTasks);

        getAllTaskUseCase.getAllTasks();
    }
}
