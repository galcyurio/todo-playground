package com.github.galcyurio.dagger2todo.mvp.contract;

import com.github.galcyurio.dagger2todo.commons.Events;
import com.github.galcyurio.dagger2todo.commons.FilterType;
import com.github.galcyurio.dagger2todo.data.model.Task;
import com.github.galcyurio.dagger2todo.mvp.BasePresenter;
import com.github.galcyurio.dagger2todo.mvp.BaseView;

import java.util.List;

/**
 * @author galcyurio
 */
public interface TaskContract {

    interface View extends BaseView {
        void showAddTaskUi();

        void showFilterPopupUi();

        void appendTasks(List<Task> tasks);

        void clearTasks();
    }

    interface Presenter extends BasePresenter {
        void onAddTaskBtnClicked(Events.AddTaskBtnClickEvent event);

        void onFilterBtnClicked(Events.FilterBtnClickEvent event);

        void onFilterPopupClicked(Events.FilterPopupClickEvent event);

        List<Task> fetchTasks(FilterType type);
    }
}
