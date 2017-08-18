package com.github.galcyurio.freetodo.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author galcyurio
 */
public class Events {

    // ~ TaskActivity
    public static final class AddTaskBtnClickEvent {}

    public static final class FilterBtnClickEvent {}

    @AllArgsConstructor @Getter
    public static final class FilterPopupClickEvent {
        private FilterType type;
    }

    // ~ AddTaskActivity
    @AllArgsConstructor @Getter
    public static final class WriteBtnClickEvent {
        private String title;
        private String description;
    }
}
