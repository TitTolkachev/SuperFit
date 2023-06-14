package com.example.superfit.presentation.helper

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object PhotoDateMapper {

    fun mapUploadedDateToString(date: Long): String {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val res = convertSecondsToLocalDateTime(date = date)
        return formatter.format(res)
    }

    fun convertSecondsToLocalDateTime(date: Long): LocalDateTime {
        val instant = Instant.ofEpochMilli(date * 1000L)

        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    }
}