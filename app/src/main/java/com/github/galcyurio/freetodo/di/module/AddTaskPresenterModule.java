package com.github.galcyurio.freetodo.di.module;

import com.github.galcyurio.freetodo.mvp.contract.AddTaskContract;
import com.github.galcyurio.freetodo.mvp.presenter.AddTaskPresenter;
import com.github.galcyurio.freetodo.mvp.view.activity.AddTaskActivity;
import com.github.galcyurio.freetodo.mvp.view.activity.TaskActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author galcyurio
 */
@Module
public abstract class AddTaskPresenterModule {

    @ContributesAndroidInjector
    abstract TaskActivity taskActivity();

    @Binds
    abstract AddTaskContract.View provideView(AddTaskActivity view);

    @Binds
    abstract AddTaskContract.Presenter providePresenter(AddTaskPresenter presenter);
}
