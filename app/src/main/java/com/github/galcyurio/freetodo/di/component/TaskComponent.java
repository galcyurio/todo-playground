package com.github.galcyurio.freetodo.di.component;

import com.github.galcyurio.freetodo.di.module.TaskAdapterModule;
import com.github.galcyurio.freetodo.di.module.TaskPresenterModule;
import com.github.galcyurio.freetodo.di.scope.PresenterScope;
import com.github.galcyurio.freetodo.mvp.view.activity.TaskActivity;

import dagger.Component;

/**
 * @author galcyurio
 */
@PresenterScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = {TaskPresenterModule.class, TaskAdapterModule.class})
public interface TaskComponent {
    void inject(TaskActivity activity);
}
