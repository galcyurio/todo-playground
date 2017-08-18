package com.github.galcyurio.freetodo.app;

import com.github.galcyurio.freetodo.BuildConfig;
import com.github.galcyurio.freetodo.commons.ReleaseTree;
import com.github.galcyurio.freetodo.di.component.ApplicationComponent;
import com.github.galcyurio.freetodo.di.component.DaggerApplicationComponent;
import com.github.galcyurio.freetodo.di.module.ApplicationModule;
import com.github.galcyurio.freetodo.di.module.DatabaseModule;

import android.app.Application;
import android.content.Context;
import timber.log.Timber;

/**
 * @author galcyurio
 */
public class TodoApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initTimber();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .databaseModule(new DatabaseModule())
                .build();
    }

    public static TodoApplication get(Context context) {
        return (TodoApplication) context.getApplicationContext();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
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
