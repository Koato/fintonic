package com.erureka.fintonic.application.usecases;

import com.erureka.fintonic.domain.models.Task;
import com.erureka.fintonic.domain.ports.in.CreateTaskUseCase;
import com.erureka.fintonic.domain.ports.out.TaskRepositoryPort;
import com.erureka.fintonic.infrastructure.in.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CreateTaskUseCaseImpl implements CreateTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;
    private final TaskMapper taskMapper;

    @Override
    public Optional<Task> createTask(Task task) {
        return Optional.of(taskMapper.toArchitect(taskRepositoryPort.create(taskMapper.toDocument(task))));
    }
}
