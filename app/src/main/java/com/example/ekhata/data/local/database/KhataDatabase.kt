package com.example.ekhata.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ekhata.data.local.dao.CustomerDao
import com.example.ekhata.data.local.dao.TransactionDao
import com.example.ekhata.data.local.entity.CustomerEntity
import com.example.ekhata.data.local.entity.TransactionEntity

@Database(
    entities = [
        CustomerEntity::class,
        TransactionEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class KhataDatabase: RoomDatabase() {
    abstract fun customerDao(): CustomerDao
    abstract fun transactionDao(): TransactionDao

    companion object{
        const val DATABASE_NAME = "ekhata_db"
    }
}