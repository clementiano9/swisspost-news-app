package com.clement.newsapp.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.formatDate(): String {
    val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    try {
        val date = inputFormatter.parse(this)

        val outputFormatter = SimpleDateFormat("HH:mm:ss dd-MM", Locale.getDefault())
        return outputFormatter.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
        return this
    } catch (e: IllegalArgumentException) {
        e.printStackTrace()
        return this
    } catch (e: Exception) {
        e.printStackTrace()
        return this
    }
}