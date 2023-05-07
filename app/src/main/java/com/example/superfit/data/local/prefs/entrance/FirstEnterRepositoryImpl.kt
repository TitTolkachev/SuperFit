package com.example.superfit.data.local.prefs.entrance

import com.example.superfit.domain.repository.local.FirstEnterRepository

class FirstEnterRepositoryImpl(private val storage: FirstEnterStorage) : FirstEnterRepository {

    override fun getEntranceInfo(): Boolean {

        return storage.getEntranceInfo()
    }

    override fun saveEntranceInfo(value: Boolean) {

        storage.saveEntranceInfo(value)
    }
}