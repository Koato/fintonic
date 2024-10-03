package com.erureka.fintonic.domain.ports.in;

import com.erureka.fintonic.domain.models.Task;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public interface CreateTaskUseCase {

    Optional<Task> createTask(Task task);
}
