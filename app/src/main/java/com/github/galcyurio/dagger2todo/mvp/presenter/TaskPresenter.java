package com.github.galcyurio.dagger2todo.mvp.presenter;

import com.github.galcyurio.dagger2todo.data.source.LocalTaskRepository;
import com.github.galcyurio.dagger2todo.mvp.contract.TaskContract;

import javax.inject.Inject;

/**
 * @author galcyurio
 */
public final class TaskPresenter implements TaskContract.Presenter {

    private final TaskContract.View mView;
    private final LocalTaskRepository mTaskRepository;

    @Inject
    public TaskPresenter(TaskContract.View view, LocalTaskRepository taskRepository) {
        mView = view;
        mTaskRepository = taskRepository;
    }
}
