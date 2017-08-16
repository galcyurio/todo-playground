package com.github.galcyurio.dagger2todo.di.component;

import com.github.galcyurio.dagger2todo.di.module.AddTaskPresenterModule;
import com.github.galcyurio.dagger2todo.di.scope.PresenterScope;
import com.github.galcyurio.dagger2todo.mvp.view.activity.AddTaskActivity;

import dagger.Component;

/**
 * @author galcyurio
 */
@PresenterScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = AddTaskPresenterModule.class
)
public interface AddTaskComponent {
    void inject(AddTaskActivity activity);
}
