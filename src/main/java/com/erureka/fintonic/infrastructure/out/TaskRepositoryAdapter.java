package com.erureka.fintonic.infrastructure.out;

import com.erureka.fintonic.domain.ports.out.TaskRepositoryPort;
import com.erureka.fintonic.infrastructure.in.dto.TaskDTO;
import com.erureka.fintonic.infrastructure.out.mapper.TaskPersistenceMapper;
import com.erureka.fintonic.infrastructure.out.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TaskRepositoryAdapter implements TaskRepositoryPort {

    private final TaskRepository taskRepository;
    private final TaskPersistenceMapper mapper;

    @Override
    public TaskDTO create(TaskDTO task) {
        return mapper.toDomain(taskRepository.insert(mapper.toArchitect(task)));
    }

    @Override
    public List<TaskDTO> findAll() {
        return taskRepository.findAll().stream().map(mapper::toDomain).toList();
    }
}
