package com.example.ekhata.features.customer.domain.model

data class Customer(
    val id: Int = 0,

    val name: String,

    val phoneNumber: String,

    val address: String = "",

    val totalPendingAmount: Double = 0.0,

    val createdAt: Long = System.currentTimeMillis(),

    val fcmToken: String = "",

    val profileImageUri: String? = null
)