package com.github.galcyurio.dagger2todo.mvp.view.activity;

import com.github.galcyurio.dagger2todo.R;
import com.github.galcyurio.dagger2todo.app.BaseActivity;
import com.github.galcyurio.dagger2todo.app.TodoApplication;
import com.github.galcyurio.dagger2todo.commons.BusProvider;
import com.github.galcyurio.dagger2todo.commons.Events;
import com.github.galcyurio.dagger2todo.di.component.DaggerAddTaskComponent;
import com.github.galcyurio.dagger2todo.di.module.AddTaskPresenterModule;
import com.github.galcyurio.dagger2todo.mvp.contract.AddTaskContract;
import com.github.galcyurio.dagger2todo.mvp.presenter.AddTaskPresenter;

import javax.inject.Inject;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author galcyurio
 */
public class AddTaskActivity extends BaseActivity implements AddTaskContract.View {

    @Inject AddTaskPresenter mAddTaskPresenter;
    @BindView(R.id.ata_btnWrite) View mBtnWrite;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        ButterKnife.bind(this);
        bindEvents();

        DaggerAddTaskComponent.builder()
                .applicationComponent(TodoApplication.get(this).getApplicationComponent())
                .addTaskPresenterModule(new AddTaskPresenterModule(this))
                .build().inject(this);

        ActionBar actionBar = checkNotNull(getActionBar());
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.string_new_task);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAddTaskPresenter.registerBus();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAddTaskPresenter.unregisterBus();
    }

    @Override
    public void bindEvents() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.ata_btnWrite:
                        BusProvider.get().post(new Events.WriteBtnClickEvent());
                        break;
                }
            }
        };

        mBtnWrite.setOnClickListener(onClickListener);
    }

    @Override
    public void bindEvents(Object view) {

    }

    @Override
    public void showTaskListUi() {
        finish();
    }
}
