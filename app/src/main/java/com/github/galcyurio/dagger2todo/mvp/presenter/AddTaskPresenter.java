package com.github.galcyurio.dagger2todo.mvp.presenter;

import com.github.galcyurio.dagger2todo.commons.BusProvider;
import com.github.galcyurio.dagger2todo.commons.Events;
import com.github.galcyurio.dagger2todo.data.source.LocalTaskRepository;
import com.github.galcyurio.dagger2todo.mvp.contract.AddTaskContract;
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
    public void registerBus() {
        BusProvider.get().register(this);
    }

    @Override
    public void unregisterBus() {
        BusProvider.get().unregister(this);
    }

    @Subscribe
    public void onWriteBtnClickEvent(Events.WriteBtnClickEvent event) {
        // TODO: repository 에 저장
        mView.showTaskListUi();
    }
}
