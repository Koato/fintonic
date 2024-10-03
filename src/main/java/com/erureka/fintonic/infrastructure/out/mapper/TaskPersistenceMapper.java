package com.erureka.fintonic.infrastructure.out.mapper;

import com.erureka.fintonic.infrastructure.in.dto.TaskDTO;
import com.erureka.fintonic.infrastructure.out.documents.TaskDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskPersistenceMapper {

    TaskDTO toDomain(TaskDocument taskDocument);
    TaskDocument toArchitect(TaskDTO taskDTO);
}
