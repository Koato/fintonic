package com.erureka.fintonic.infrastructure.out.repository;

import com.erureka.fintonic.infrastructure.out.documents.TaskDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<TaskDocument, String> {

}
