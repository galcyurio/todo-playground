package com.github.galcyurio.dagger2todo.di.component;

import com.github.galcyurio.dagger2todo.data.source.DbHelper;
import com.github.galcyurio.dagger2todo.data.source.LocalTaskRepository;
import com.github.galcyurio.dagger2todo.di.module.ApplicationModule;
import com.github.galcyurio.dagger2todo.di.module.DatabaseModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author galcyurio
 */
@Singleton
@Component(modules = {ApplicationModule.class, DatabaseModule.class})
public interface ApplicationComponent {
    DbHelper getDbHelper();
    LocalTaskRepository getLocalTaskRepository();
}
