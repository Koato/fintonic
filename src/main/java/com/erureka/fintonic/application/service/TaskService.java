package com.erureka.fintonic.application.service;

import com.erureka.fintonic.domain.model.Task;
import com.erureka.fintonic.domain.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * <b>Class</b>: TaskService <br/>
 */
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    /**
     * Crear una nueva tarea
     * @param task
     * @return Task
     */
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Listar todas las tareas
     * @return List
     */
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
