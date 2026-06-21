package com.example.ekhata.util.AppUtil

object DateUtils {

    @JvmStatic
    fun getTimeAgo(timeStamp: Long): String {
        val diff = System.currentTimeMillis() - timeStamp

        val days = diff / (1000 * 60 * 60* 24)

        return when{
            days == 0L -> "Today"
            days == 1L -> "1 day ago"
            else -> "$days days ago"
        }
    }
}