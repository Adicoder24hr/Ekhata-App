package com.example.ekhata.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "transactions",
    foreignKeys = [
        ForeignKey(
            entity = CustomerEntity::class,
            parentColumns = ["id"],
            childColumns = ["customerId"],
            onDelete = ForeignKey.CASCADE   // if customer is deleted, delete all transactions
        )
    ],
    indices = [Index(value = ["customerId"])])
class TransactionEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val customerId: Int,

    val amount: Double,

    val type: String,

    val description: String = "",

    val date: Long = System.currentTimeMillis(),

    val createdAt: Long = System.currentTimeMillis()
)