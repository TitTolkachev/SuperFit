package com.example.superfit.domain.usecase.local

import com.example.superfit.domain.repository.local.FirstEnterRepository
import javax.inject.Inject

class SaveEntranceInfoUseCase @Inject constructor(private val repository: FirstEnterRepository) {

    fun execute(value: Boolean) {
        return repository.saveEntranceInfo(value)
    }
}