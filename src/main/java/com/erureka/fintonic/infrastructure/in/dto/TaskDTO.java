package com.erureka.fintonic.infrastructure.in.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public record TaskDTO (

    String id,

    @NotNull(message = "title Cannot be null")
    String title,

    @NotNull(message = "description Cannot be null")
    String description,

    @NotNull(message = "dueDate Cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    LocalDate dueDate,

    @NotNull(message = "tags Cannot be null")
    List<String> tags,

    Boolean completed
) implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;

}
