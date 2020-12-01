package com.github.galcyurio.todo

import android.content.Context
import androidx.room.Room
import com.github.galcyurio.todo.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
interface FakeDatabaseModule {
    companion object {
        @Singleton
        @Provides
        fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
            return Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        }
    }
}
