package com.example.superfit.data.local.prefs.entrance

interface FirstEnterStorage {

    fun getEntranceInfo(): Boolean

    fun saveEntranceInfo(value: Boolean)
}