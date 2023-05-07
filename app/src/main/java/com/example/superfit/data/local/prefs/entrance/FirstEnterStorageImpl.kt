package com.example.superfit.data.local.prefs.entrance

import android.content.Context
import android.content.SharedPreferences

private const val APP_PREFERENCES = "preferences_settings"
private const val FIRST_ENTER_NAME = "first_enter"

class FirstEnterStorageImpl(context: Context) : FirstEnterStorage {

    private val preferences: SharedPreferences =
        context.getSharedPreferences(
            APP_PREFERENCES,
            Context.MODE_PRIVATE
        )

    override fun getEntranceInfo(): Boolean {

        return preferences.getBoolean(FIRST_ENTER_NAME, false)
    }

    override fun saveEntranceInfo(value: Boolean) {

        preferences.edit().putBoolean(FIRST_ENTER_NAME, value).apply()
    }
}