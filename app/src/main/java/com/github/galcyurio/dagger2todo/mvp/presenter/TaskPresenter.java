package com.github.galcyurio.dagger2todo.mvp.presenter;

import com.github.galcyurio.dagger2todo.commons.BusProvider;
import com.github.galcyurio.dagger2todo.commons.Events;
import com.github.galcyurio.dagger2todo.commons.FilterType;
import com.github.galcyurio.dagger2todo.data.model.Task;
import com.github.galcyurio.dagger2todo.data.source.LocalTaskRepository;
import com.github.galcyurio.dagger2todo.mvp.contract.TaskContract;
import com.google.common.collect.Lists;
import com.google.common.eventbus.Subscribe;

import java.util.List;

import javax.inject.Inject;

/**
 * @author galcyurio
 */
public final class TaskPresenter implements TaskContract.Presenter {

    private final TaskContract.View mView;
    private final LocalTaskRepository mTaskRepository;

    @Inject
    TaskPresenter(TaskContract.View view, LocalTaskRepository taskRepository) {
        mView = view;
        mTaskRepository = taskRepository;
    }

    @Override
    public void registerBus() {
        BusProvider.get().register(this);
    }

    @Override
    public void unregisterBus() {
        BusProvider.get().unregister(this);
    }

    @Subscribe
    @Override
    public void onAddTaskBtnClicked(Events.AddTaskBtnClickEvent event) {
        mView.showAddTaskUi();
    }

    @Subscribe
    @Override
    public void onFilterBtnClicked(Events.FilterBtnClickEvent event) {
        mView.showFilterPopupUi();
    }

    @Subscribe
    @Override
    public void onFilterPopupClicked(Events.FilterPopupClickEvent event) {
        FilterType type = event.getType();
        List<Task> tasks = fetchTasks(type);
        mView.appendTasks(tasks);
    }

    @Override
    public List<Task> fetchTasks(FilterType type) {
        List<Task> tasks = Lists.newArrayList();

        // TODO: get tasks from repository
        switch (type) {
            case ALL:
                break;
            case ACTIVE:
                break;
            case COMPLETED:
                break;
        }
        return tasks;
    }
}
