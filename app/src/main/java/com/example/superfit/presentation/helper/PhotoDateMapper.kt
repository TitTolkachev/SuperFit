package com.example.superfit.presentation.helper

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object PhotoDateMapper {

    fun mapUploadedDateToString(date: Long): String {

        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

        val instant = Instant.ofEpochMilli(date * 1000L)

        val res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        return formatter.format(res)
    }
}