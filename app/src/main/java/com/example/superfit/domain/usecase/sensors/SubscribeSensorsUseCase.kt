package com.example.superfit.domain.usecase.sensors

import com.example.superfit.domain.model.Exercises
import com.example.superfit.domain.repository.sensors.SensorsRepository
import javax.inject.Inject

class SubscribeSensorsUseCase @Inject constructor(private val repository: SensorsRepository) {

    fun execute(exercise: Exercises) {
        repository.subscribe(exercise)
    }
}