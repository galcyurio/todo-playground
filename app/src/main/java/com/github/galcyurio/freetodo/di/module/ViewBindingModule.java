package com.github.galcyurio.freetodo.di.module;

import com.github.galcyurio.freetodo.mvp.view.activity.AddTaskActivity;
import com.github.galcyurio.freetodo.mvp.view.activity.TaskActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author galcyurio
 */
@Module
public abstract class ViewBindingModule {

    @ContributesAndroidInjector(modules = {TaskPresenterModule.class, TaskAdapterModule.class})
    abstract TaskActivity taskActivity();

    @ContributesAndroidInjector(modules = AddTaskPresenterModule.class)
    abstract AddTaskActivity addTaskActivity();
}
