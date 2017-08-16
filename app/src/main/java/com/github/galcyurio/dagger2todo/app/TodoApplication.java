package com.github.galcyurio.dagger2todo.app;

import com.github.galcyurio.dagger2todo.di.component.ApplicationComponent;
import com.github.galcyurio.dagger2todo.di.component.DaggerApplicationComponent;
import com.github.galcyurio.dagger2todo.di.module.ApplicationModule;
import com.github.galcyurio.dagger2todo.di.module.DatabaseModule;

import android.app.Application;
import android.content.Context;

/**
 * @author galcyurio
 */
public class TodoApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

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
}
