package com.erureka.fintonic.infrastructure.in.controllers;

import com.erureka.fintonic.domain.ports.in.GetAllTaskUseCase;
import com.erureka.fintonic.domain.ports.in.CreateTaskUseCase;
import com.erureka.fintonic.infrastructure.in.dto.TaskDTO;
import com.erureka.fintonic.infrastructure.in.mapper.TaskMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final GetAllTaskUseCase getAllTaskUseCase;
    private final TaskMapper taskMapper;

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody TaskDTO taskDTO) {
        return createTaskUseCase.createTask(taskMapper.toArchitect(taskDTO))
                .map(task -> new ResponseEntity(taskMapper.toDocument(task), HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return ResponseEntity.ok(getAllTaskUseCase.getAllTasks().stream()
                .map(taskMapper::toDocument).toList());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        DefaultMessageSourceResolvable::getDefaultMessage
                ));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
