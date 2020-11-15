package com.github.galcyurio.todo

import android.content.Context
import androidx.room.Room
import com.github.galcyurio.todo.data.AppDatabase
import com.github.galcyurio.todo.data.TaskDao
import com.github.galcyurio.todo.data.TaskRepositoryImpl
import com.github.galcyurio.todo.domain.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
interface DataModule {
    @Binds
    fun bindTaskRepository(repository: TaskRepositoryImpl): TaskRepository

    companion object {
        @Provides
        @Singleton
        fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "tasks.db"
            ).build()
        }

        @Provides
        fun provideTaskDao(database: AppDatabase): TaskDao = database.taskDao()
    }
}