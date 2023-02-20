package com.github.galcyurio.todo

import com.github.galcyurio.todo.data.TaskDataSource
import com.github.galcyurio.todo.data.TaskRepositoryImpl
import com.github.galcyurio.todo.data.remote.ApiService
import com.github.galcyurio.todo.domain.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@InstallIn(SingletonComponent::class)
@Module
interface FakeRemoteModule {
    @Binds
    fun bindTaskDataSource(dataSource: FakeTaskDataSource): TaskDataSource

    @Binds
    fun bindTaskRepository(repository: TaskRepositoryImpl): TaskRepository

    companion object {
        @Provides
        fun provideApiService(baseUrl: String): ApiService {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create()
        }
    }
}
