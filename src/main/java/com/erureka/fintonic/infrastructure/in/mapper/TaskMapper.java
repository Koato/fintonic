package com.erureka.fintonic.infrastructure.in.mapper;

import com.erureka.fintonic.domain.models.Task;
import com.erureka.fintonic.infrastructure.in.dto.TaskDTO;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDTO toDocument(Task task);
    Task toArchitect(TaskDTO taskDTO);
    List<Task> toTasks(List<TaskDTO> taskDTO);
}
