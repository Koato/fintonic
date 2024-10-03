package com.erureka.fintonic;

import com.erureka.fintonic.infrastructure.in.dto.TaskDTO;
import com.erureka.fintonic.infrastructure.out.TaskRepositoryAdapter;
import com.erureka.fintonic.infrastructure.out.documents.TaskDocument;
import com.erureka.fintonic.infrastructure.out.mapper.TaskPersistenceMapper;
import com.erureka.fintonic.infrastructure.out.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TaskRepositoryAdapterTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskPersistenceMapper mapper;

    @InjectMocks
    private TaskRepositoryAdapter taskRepositoryAdapter;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(taskRepositoryAdapter).build();
    }

    @Test
    void testCreate() {
        var taskDocument = new TaskDocument();
        taskDocument.setDueDate(LocalDate.now());
        taskDocument.setDescription("description");
        taskDocument.setTitle("title");
        taskDocument.setTags(Arrays.asList("tag1", "tag2"));
        taskDocument.setCompleted(true);

        var taskDTO = new TaskDTO("id", taskDocument.getTitle(), taskDocument.getDescription(), taskDocument.getDueDate(), taskDocument.getTags(), false);
        when(mapper.toArchitect(any())).thenReturn(taskDocument);
        when(taskRepository.insert(taskDocument)).thenReturn(taskDocument);
        when(mapper.toDomain(taskDocument)).thenReturn(taskDTO);

        var task = taskRepositoryAdapter.create(taskDTO);
        Assert.notNull(task, "Con informacion");
    }

    @Test
    void testgetTasks() {
        var taskDocument = new TaskDocument();
        taskDocument.setDueDate(LocalDate.now());
        taskDocument.setDescription("description");
        taskDocument.setTitle("title");
        taskDocument.setTags(Arrays.asList("tag1", "tag2"));
        taskDocument.setCompleted(true);

        var taskDTO = new TaskDTO("id", taskDocument.getTitle(), taskDocument.getDescription(), taskDocument.getDueDate(), taskDocument.getTags(), false);
        var lista = List.of(taskDocument);

        when(taskRepository.findAll()).thenReturn(lista);
        when(mapper.toDomain(taskDocument)).thenReturn(taskDTO);

        var task = taskRepositoryAdapter.findAll();
        Assert.notNull(task, "Con informacion");
    }
}