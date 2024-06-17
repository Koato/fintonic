package com.erureka.fintonic.infrastructure.repository;

import com.erureka.fintonic.domain.model.Task;
import com.erureka.fintonic.domain.repository.TaskRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class</b>: MongoTaskRepository <br/>
 */
@Repository
public interface MongoTaskRepository extends MongoRepository<Task, String>, TaskRepository {

}
