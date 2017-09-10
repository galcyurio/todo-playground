package com.github.galcyurio.freetodo.app;

import com.github.galcyurio.freetodo.BuildConfig;
import com.github.galcyurio.freetodo.commons.log.ReleaseTree;
import com.github.galcyurio.freetodo.di.component.ApplicationComponent;
import com.github.galcyurio.freetodo.di.component.DaggerApplicationComponent;
import com.github.galcyurio.freetodo.di.module.ApplicationModule;
import com.github.galcyurio.freetodo.di.module.DatabaseModule;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import timber.log.Timber;

/**
 * @author galcyurio
 */
public class TodoApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initTimber();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        ApplicationComponent component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .databaseModule(new DatabaseModule())
                .build();
        component.inject(this);
        return component;
    }

    private void initTimber() {
        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new ReleaseTree());
        }
        Timber.i("Timber initialization success!");
    }
}
