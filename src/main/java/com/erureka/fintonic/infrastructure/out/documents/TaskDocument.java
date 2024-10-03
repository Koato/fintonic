package com.erureka.fintonic.infrastructure.out.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Document(collection = "tasks")
public class TaskDocument implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Field(name = "id")
    private String id;

    @Field(name = "tittle")
    private String title;

    @Field(name = "description")
    private String description;

    @Field(name = "dueDate")
    private LocalDate dueDate;

    @Field(name = "tags")
    private List<String> tags;

    @Field(name = "completed", write = Field.Write.ALWAYS)
    private Boolean completed;
}
