package com.github.galcyurio.todo.data.remote

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("tasks")
    suspend fun saveTask(task: TaskRemote)

    @DELETE("tasks")
    suspend fun deleteTask(task: TaskRemote)

    @POST("tasks/{id}")
    suspend fun getTask(@Path("id") id: Long): TaskRemote

    @GET("tasks")
    suspend fun getTasks(): List<TaskRemote>

    @PATCH("tasks")
    suspend fun update(toRemote: TaskRemote)
}
