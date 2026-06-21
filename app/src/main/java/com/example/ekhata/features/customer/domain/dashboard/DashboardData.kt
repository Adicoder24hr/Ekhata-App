package com.example.ekhata.features.customer.domain.dashboard

import com.example.ekhata.features.customer.domain.model.Customer

data class DashboardData(
    val totalUdhar: Double,
    val receivedToday: Double,
    val totalCustomers: Int,
    val pendingCustomers: List<Customer>
)