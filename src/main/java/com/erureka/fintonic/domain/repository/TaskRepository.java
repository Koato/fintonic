package com.erureka.fintonic.domain.repository;

import com.erureka.fintonic.domain.model.Task;
import java.util.List;

/**
 * <b>Class</b>: TaskRepository <br/>
 */
public interface TaskRepository {

    Task save(Task task);
    List<Task> findAll();
}
