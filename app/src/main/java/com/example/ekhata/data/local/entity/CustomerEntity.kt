package com.example.ekhata.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customers")
data class CustomerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,

    val phoneNumber: String,

    val address: String = "",

    val totalPendingAmount: Double = 0.0,

    val createdAt: Long = System.currentTimeMillis(),

    val fcmToken: String = ""
)