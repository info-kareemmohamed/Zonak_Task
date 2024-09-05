package com.example.myapplication.core


import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.formatDateTime(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())

    inputFormat.timeZone = TimeZone.getTimeZone("UTC")

    return try {
        val date = inputFormat.parse(this) ?: return "Invalid date"
        outputFormat.timeZone = TimeZone.getDefault()
        outputFormat.format(date)
    } catch (e: ParseException) {
        "Invalid date"
    }
}