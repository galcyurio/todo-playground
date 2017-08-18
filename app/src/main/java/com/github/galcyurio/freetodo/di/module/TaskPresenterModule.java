package com.github.galcyurio.freetodo.di.module;

import com.github.galcyurio.freetodo.mvp.contract.TaskContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author galcyurio
 */
@Module
public class TaskPresenterModule {

    private final TaskContract.View mView;

    public TaskPresenterModule(TaskContract.View view) {
        mView = view;
    }

    @Provides
    TaskContract.View provideView() {
        return mView;
    }

}
