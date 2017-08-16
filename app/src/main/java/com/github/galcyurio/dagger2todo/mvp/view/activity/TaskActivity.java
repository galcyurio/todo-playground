package com.github.galcyurio.dagger2todo.mvp.view.activity;

import com.github.galcyurio.dagger2todo.R;
import com.github.galcyurio.dagger2todo.app.TodoApplication;
import com.github.galcyurio.dagger2todo.data.source.LocalTaskRepository;
import com.github.galcyurio.dagger2todo.di.component.DaggerTaskComponent;
import com.github.galcyurio.dagger2todo.di.module.TaskPresenterModule;
import com.github.galcyurio.dagger2todo.mvp.contract.TaskContract;
import com.github.galcyurio.dagger2todo.mvp.presenter.TaskPresenter;

import javax.inject.Inject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class TaskActivity extends Activity implements TaskContract.View {

    @Inject TaskPresenter mPresenter;
    @Inject LocalTaskRepository mLocalTaskRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        DaggerTaskComponent.builder()
                .applicationComponent(TodoApplication.get(this).getApplicationComponent())
                .taskPresenterModule(new TaskPresenterModule(this))
                .build()
                .inject(this);

        Log.i(getClass().getName(), "mPresenter = " + mPresenter);
    }
}
