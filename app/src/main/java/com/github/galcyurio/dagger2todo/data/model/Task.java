package com.github.galcyurio.dagger2todo.data.model;

import lombok.Data;

/**
 * @author galcyurio
 */
@Data
public class Task {
    private String id;
    private String title;
    private String description;
    private Boolean isCompleted;
}
