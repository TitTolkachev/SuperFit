package com.example.superfit.domain.repository.local

interface FirstEnterRepository {

    fun getEntranceInfo(): Boolean

    fun saveEntranceInfo(value: Boolean)
}