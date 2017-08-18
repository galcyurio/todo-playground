package com.github.galcyurio.freetodo.di.component;

import com.github.galcyurio.freetodo.di.module.AddTaskPresenterModule;
import com.github.galcyurio.freetodo.di.scope.PresenterScope;
import com.github.galcyurio.freetodo.mvp.view.activity.AddTaskActivity;

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
