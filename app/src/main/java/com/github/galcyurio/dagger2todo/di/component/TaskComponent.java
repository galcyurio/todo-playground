package com.github.galcyurio.dagger2todo.di.component;

import com.github.galcyurio.dagger2todo.di.module.TaskPresenterModule;
import com.github.galcyurio.dagger2todo.di.scope.PresenterScope;
import com.github.galcyurio.dagger2todo.mvp.view.activity.TaskActivity;

import dagger.Component;

/**
 * @author galcyurio
 */
@PresenterScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = TaskPresenterModule.class)
public interface TaskComponent {
    void inject(TaskActivity activity);
}
