package com.github.galcyurio.freetodo.data.model;

import java.util.UUID;

import android.support.annotation.NonNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author galcyurio
 */
@Data
public class Task {
    private Long id;
    private String uuid;
    private String title;
    private String description;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Boolean isCompleted;

    public Task() {
        this(null, null);
    }

    public Task(String title, String description) {
        this(title, description, false);
    }

    public Task(String title, String description, Boolean isCompleted) {
        this(title, description, isCompleted, UUID.randomUUID().toString());
    }

    public Task(String title, String description, @NonNull String uuid) {
        this(title, description, false, uuid);
    }

    public Task(String title, String description, Boolean isCompleted, String uuid) {
        this(title, description, isCompleted, uuid, null);
    }

    public Task(String title, String description, Boolean isCompleted, String uuid, Long id) {
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.uuid = uuid;
        this.id = id;
    }

    public Boolean isCompleted() {return isCompleted;}

    public void setCompleted(Boolean isCompleted) { this.isCompleted = isCompleted; }
}
