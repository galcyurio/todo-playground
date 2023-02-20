package com.github.galcyurio.todo.di

//import com.github.galcyurio.todo.data.TaskDataSource
//import com.github.galcyurio.todo.data.TaskRepositoryImpl
//import com.github.galcyurio.todo.data.local.AppDatabase
//import com.github.galcyurio.todo.data.local.LocalTaskDataSource
//import com.github.galcyurio.todo.data.local.TaskDao
//import com.github.galcyurio.todo.domain.TaskRepository
//import dagger.Binds
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//
//@InstallIn(SingletonComponent::class)
//@Module
//interface LocalModule {
//    @Binds
//    fun bindTaskDataSource(dataSource: LocalTaskDataSource): TaskDataSource
//
//    @Binds
//    fun bindTaskRepository(repository: TaskRepositoryImpl): TaskRepository
//
//    companion object {
//        @Provides
//        fun provideTaskDao(database: AppDatabase): TaskDao = database.taskDao()
//    }
//}
