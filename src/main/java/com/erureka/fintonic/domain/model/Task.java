package com.erureka.fintonic.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

/**
 * <b>Class</b>: Task <br/>
 */
@Data
public class Task {

    private String id;

    @NotNull(message = "Cannot be null")
    private String title;

    @NotNull(message = "Cannot be null")
    private String description;

    @NotNull(message = "Cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dueDate;

    @NotNull(message = "Cannot be null")
    private List<String> tags;

    private boolean completed;
}
