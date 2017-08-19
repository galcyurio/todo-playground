package com.github.galcyurio.freetodo.di.module;

import com.github.galcyurio.freetodo.mvp.adapter.TaskAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * @author galcyurio
 */
@Module
public class TaskAdapterModule {

    @Provides
    public TaskAdapter provideTaskAdapter() {
        return new TaskAdapter();
    }
}
