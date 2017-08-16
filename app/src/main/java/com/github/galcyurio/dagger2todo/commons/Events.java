package com.github.galcyurio.dagger2todo.commons;

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
    public static final class WriteBtnClickEvent {}
}
