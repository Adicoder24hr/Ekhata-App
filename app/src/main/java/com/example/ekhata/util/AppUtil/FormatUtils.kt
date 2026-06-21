package com.example.ekhata.util.AppUtil

object FormatUtils {

    @JvmStatic
    fun formatAmount(amount: Double): String {
        return "₹${amount.toInt()}"
    }
}