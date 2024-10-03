package com.erureka.fintonic.application.usecases;

import com.erureka.fintonic.domain.models.Task;
import com.erureka.fintonic.domain.ports.in.GetAllTaskUseCase;
import com.erureka.fintonic.domain.ports.out.TaskRepositoryPort;
import com.erureka.fintonic.infrastructure.in.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@RequiredArgsConstructor
@Component
public class GetAllTaskUseCaseImpl implements GetAllTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;
    private final TaskMapper taskMapper;

    public List<Task> getAllTasks() {
        return taskMapper.toTasks(taskRepositoryPort.findAll());
    }
}
