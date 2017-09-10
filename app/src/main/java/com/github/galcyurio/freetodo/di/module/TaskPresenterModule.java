package com.github.galcyurio.freetodo.di.module;

import com.github.galcyurio.freetodo.mvp.contract.TaskContract;
import com.github.galcyurio.freetodo.mvp.presenter.TaskPresenter;
import com.github.galcyurio.freetodo.mvp.view.activity.TaskActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author galcyurio
 */
@Module
public abstract class TaskPresenterModule {

    @ContributesAndroidInjector
    abstract TaskActivity taskActivity();

    @Binds
    abstract TaskContract.Presenter taskPresenter(TaskPresenter presenter);

    @Binds
    abstract TaskContract.View taskView(TaskActivity view);

}
