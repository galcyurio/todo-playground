package com.github.galcyurio.freetodo.commons;

import com.github.galcyurio.freetodo.data.model.Task;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author galcyurio
 */
public class Events {

    // ~ TaskActivity =========================================================
    public static final class AddTaskBtnClickEvent {}

    public static final class FilterBtnClickEvent {}

    public static final class RefreshMenuClickEvent {}

    @AllArgsConstructor @Getter
    public static final class FilterPopupClickEvent {
        private FilterType type;
    }

    @AllArgsConstructor @Getter
    public static final class TaskCheckedChangeEvent {
        private Task task;
        private boolean isChecked;
    }

    // ~ AddTaskActivity =========================================================
    @AllArgsConstructor @Getter
    public static final class WriteBtnClickEvent {
        private String title;
        private String description;
    }

    @AllArgsConstructor @Getter
    public static final class TaskSaveSuccessEvent {
        private Task task;
    }
}
