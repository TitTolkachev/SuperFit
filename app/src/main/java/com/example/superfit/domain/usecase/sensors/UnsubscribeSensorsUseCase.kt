package com.example.superfit.domain.usecase.sensors

import com.example.superfit.domain.repository.sensors.SensorsRepository
import javax.inject.Inject

class UnsubscribeSensorsUseCase @Inject constructor(private val repository: SensorsRepository) {

    fun execute() {
        repository.unsubscribe()
    }
}