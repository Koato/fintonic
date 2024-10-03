package com.erureka.fintonic.domain.ports.out;

import com.erureka.fintonic.infrastructure.in.dto.TaskDTO;
import java.util.List;

public interface TaskRepositoryPort {

    TaskDTO create(TaskDTO taskdto);
    List<TaskDTO> findAll();
}
