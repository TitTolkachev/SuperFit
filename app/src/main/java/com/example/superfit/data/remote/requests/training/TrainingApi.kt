package com.example.superfit.data.remote.requests.training

import com.example.superfit.data.remote.dto.TrainingDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface TrainingApi {

    @GET("/api/trainings")
    suspend fun getTrainings(): Response<List<TrainingDto>>

    @POST("/api/trainings")
    suspend fun saveTrainings(): Response<List<TrainingDto>>
}