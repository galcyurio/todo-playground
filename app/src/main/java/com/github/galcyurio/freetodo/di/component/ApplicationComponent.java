package com.github.galcyurio.freetodo.di.component;

import com.github.galcyurio.freetodo.data.source.DbHelper;
import com.github.galcyurio.freetodo.data.source.LocalTaskRepository;
import com.github.galcyurio.freetodo.di.module.ApplicationModule;
import com.github.galcyurio.freetodo.di.module.DatabaseModule;

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
