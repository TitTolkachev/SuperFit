package com.example.superfit.domain.usecase.local

import com.example.superfit.domain.repository.local.FirstEnterRepository
import javax.inject.Inject

class GetEntranceInfoUseCase @Inject constructor(private val repository: FirstEnterRepository) {

    fun execute(): Boolean {
        return repository.getEntranceInfo()
    }
}