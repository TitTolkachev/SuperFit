package com.example.superfit.domain.usecase.remote

import com.example.superfit.domain.model.Training
import com.example.superfit.domain.repository.remote.TrainingRepository
import com.example.superfit.domain.util.Resource
import javax.inject.Inject

class SaveTrainingUseCase @Inject constructor(private val repository: TrainingRepository) {

    suspend fun execute(training: Training): Resource<Training> {
        return repository.saveTrainings(training)
    }
}