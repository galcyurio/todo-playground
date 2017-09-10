package com.github.galcyurio.freetodo.di.component;

import com.github.galcyurio.freetodo.app.TodoApplication;
import com.github.galcyurio.freetodo.data.source.DbHelper;
import com.github.galcyurio.freetodo.data.source.LocalTaskRepository;
import com.github.galcyurio.freetodo.di.module.ApplicationModule;
import com.github.galcyurio.freetodo.di.module.DatabaseModule;
import com.github.galcyurio.freetodo.di.module.ViewBindingModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * @author galcyurio
 */
@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        ViewBindingModule.class,
        ApplicationModule.class,
        DatabaseModule.class
})
public interface ApplicationComponent extends AndroidInjector<TodoApplication> {
    DbHelper getDbHelper();

    LocalTaskRepository getLocalTaskRepository();
}
