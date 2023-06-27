package com.example.superfit.domain.repository.sensors

import com.example.superfit.domain.model.Exercises
import kotlinx.coroutines.flow.StateFlow

interface SensorsRepository {

    fun subscribe(exercise: Exercises)
    fun unsubscribe()

    val progressState: StateFlow<Int>
}