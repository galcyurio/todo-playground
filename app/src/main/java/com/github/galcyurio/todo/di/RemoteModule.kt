package com.github.galcyurio.todo.di

import com.github.galcyurio.todo.data.TaskDataSource
import com.github.galcyurio.todo.data.TaskRepositoryImpl
import com.github.galcyurio.todo.data.remote.ApiService
import com.github.galcyurio.todo.data.remote.RemoteTaskDataSource
import com.github.galcyurio.todo.domain.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@InstallIn(SingletonComponent::class)
@Module
interface RemoteModule {
    @Binds
    fun bindTaskDataSource(dataSource: RemoteTaskDataSource): TaskDataSource

    @Binds
    fun bindTaskRepository(repository: TaskRepositoryImpl): TaskRepository

    companion object {
        @Provides
        fun provideApiService(): ApiService {
            return Retrofit.Builder()
                .baseUrl("https://galcyurio.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create()
        }
    }
}
