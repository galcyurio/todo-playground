package com.github.galcyurio.todo

import com.github.galcyurio.todo.data.AppDatabase
import com.github.galcyurio.todo.data.TaskDao
import com.github.galcyurio.todo.data.TaskRepositoryImpl
import com.github.galcyurio.todo.domain.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface DataModule {
    @Binds
    fun bindTaskRepository(repository: TaskRepositoryImpl): TaskRepository

    companion object {
        @Provides
        fun provideTaskDao(database: AppDatabase): TaskDao = database.taskDao()
    }
}
