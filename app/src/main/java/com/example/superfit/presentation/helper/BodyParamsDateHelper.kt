package com.example.superfit.presentation.helper

import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

object BodyParamsDateHelper {

    fun getDate(): String {
        val formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
                .withLocale(Locale.ROOT)
                .withZone(ZoneId.from(ZoneOffset.UTC))
        return formatter.format(Date().toInstant()) ?: ""
    }
}