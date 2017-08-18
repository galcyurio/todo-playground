package com.github.galcyurio.freetodo.di.module;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;

/**
 * @author galcyurio
 */
@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Context provideContext() {
        return application.getApplicationContext();
    }
}
