package com.example.superfit.domain.usecase.local

import com.example.superfit.domain.repository.local.FirstEnterRepository
import javax.inject.Inject

class GetEntranceInfoUseCase @Inject constructor(private val repository: FirstEnterRepository) {

    /**
     * true - not first enter
     *
     * false - first enter
     */
    fun execute(): Boolean {
        return repository.getEntranceInfo()
    }
}