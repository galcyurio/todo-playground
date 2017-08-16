package com.github.galcyurio.dagger2todo.di.module;

import com.github.galcyurio.dagger2todo.data.source.DbHelper;

import javax.inject.Singleton;

import android.content.Context;
import dagger.Module;
import dagger.Provides;

/**
 * @author galcyurio
 */
@Module
public class DatabaseModule {

    @Singleton
    @Provides
    DbHelper provideDbHelper(Context context) {
        return new DbHelper(context);
    }

}
