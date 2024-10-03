package com.erureka.fintonic.domain.ports.in;

import com.erureka.fintonic.domain.models.Task;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface GetAllTaskUseCase {

    List<Task> getAllTasks();
}
