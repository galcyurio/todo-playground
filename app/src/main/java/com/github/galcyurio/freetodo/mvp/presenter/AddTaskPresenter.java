package com.github.galcyurio.freetodo.mvp.presenter;

import com.github.galcyurio.freetodo.commons.BusProvider;
import com.github.galcyurio.freetodo.commons.Events;
import com.github.galcyurio.freetodo.data.model.Task;
import com.github.galcyurio.freetodo.data.source.LocalTaskRepository;
import com.github.galcyurio.freetodo.mvp.contract.AddTaskContract;
import com.google.common.base.Strings;
import com.google.common.eventbus.Subscribe;

import javax.inject.Inject;

/**
 * @author galcyurio
 */
public class AddTaskPresenter implements AddTaskContract.Presenter {

    private AddTaskContract.View mView;
    private LocalTaskRepository mLocalTaskRepository;

    @Inject
    public AddTaskPresenter(AddTaskContract.View view, LocalTaskRepository localTaskRepository) {
        mView = view;
        mLocalTaskRepository = localTaskRepository;
    }

    @Override
    public void start() {

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
    public void onWriteBtnClickEvent(Events.WriteBtnClickEvent event) {
        String title = event.getTitle();
        String description = event.getDescription();

        if (Strings.isNullOrEmpty(title) && Strings.isNullOrEmpty(description)) {
            // when title and description both empty.
            mView.showNotSavedMessage();
            mView.showTaskListUi();
            return;
        }

        Task task = new Task(title, description);

        if(mLocalTaskRepository.saveTask(task) > 0) {
            // when success
            BusProvider.get().post(new Events.TaskSaveSuccessEvent(task));
            mView.showSuccessMessage();
            mView.showTaskListUi();
        } else {
            // when fail
            mView.showFailedMessage();
        }
    }
}
