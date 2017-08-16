package com.github.galcyurio.dagger2todo.di.module;

import com.github.galcyurio.dagger2todo.mvp.contract.AddTaskContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author galcyurio
 */
@Module
public class AddTaskPresenterModule {

    private AddTaskContract.View mView;

    public AddTaskPresenterModule(AddTaskContract.View view) {
        mView = view;
    }

    @Provides
    AddTaskContract.View provideView() {
        return mView;
    }
}
