package com.example.superfit.domain.repository.remote

import com.example.superfit.domain.model.Training
import com.example.superfit.domain.util.Resource

interface TrainingRepository {

    suspend fun getTrainings(): Resource<List<Training>>

    suspend fun saveTrainings(training: Training): Resource<Training>
}