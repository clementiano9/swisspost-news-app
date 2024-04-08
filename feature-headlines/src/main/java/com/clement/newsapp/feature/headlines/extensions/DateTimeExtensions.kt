package com.clement.newsapp.feature.headlines.extensions

import android.content.Context
import android.text.format.DateUtils
import com.clement.newsapp.core.ui.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.formatDate(context: Context): String {
    val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    try {
        val date = inputFormatter.parse(this)
        val outputFormatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
        if (DateUtils.isToday(date.time)) {
            return context.getString(R.string.today) + ", ${outputFormatter.format(date)}"
        } else if (DateUtils.isToday(date.time + DateUtils.DAY_IN_MILLIS)) {
            return context.getString(R.string.yesterday) + ", ${outputFormatter.format(date)}"
        }

        return SimpleDateFormat("hh:mm a, dd/MM", Locale.getDefault()).format(date)
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