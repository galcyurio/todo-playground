package com.github.galcyurio.freetodo.mvp.contract;

import com.github.galcyurio.freetodo.commons.Events;
import com.github.galcyurio.freetodo.commons.FilterType;
import com.github.galcyurio.freetodo.data.model.Task;
import com.github.galcyurio.freetodo.mvp.BasePresenter;
import com.github.galcyurio.freetodo.mvp.BaseView;

import java.util.List;

/**
 * @author galcyurio
 */
public interface TaskContract {

    interface View extends BaseView {
        void bindEvents(Object view);

        void showAddTaskUi();

        void showFilterPopupUi();

        void addTasks(List<Task> tasks);

        void addTask(Task task);

        void clearTasks();
    }

    interface Presenter extends BasePresenter {
        void onAddTaskBtnClicked(Events.AddTaskBtnClickEvent event);

        void onFilterBtnClicked(Events.FilterBtnClickEvent event);

        void onFilterPopupClicked(Events.FilterPopupClickEvent event);

        void onRefreshMenuClicked(Events.RefreshMenuClickEvent event);

        void onTaskSaveSuccessed(Events.TaskSaveSuccessEvent event);

        void onTaskCheckedChangeEvent(Events.TaskCheckedChangeEvent event);

        List<Task> fetchTasks(FilterType type);
    }
}
