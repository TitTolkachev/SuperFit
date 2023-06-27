package com.example.superfit.domain.usecase.sensors

import com.example.superfit.domain.repository.sensors.SensorsRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ObserveSensorsUseCase @Inject constructor(private val repository: SensorsRepository) {

    fun execute(): StateFlow<Int> {
        return repository.progressState
    }
}