package com.example.ekhata.repository.repo

import com.example.ekhata.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    fun getAllTransactions(): Flow<List<TransactionEntity>>

    fun getTransactionsForCustomer(customerId: Int): Flow<List<TransactionEntity>>

    fun getTotalPendingForCustomer(customerId: Int): Flow<Double?>

    fun getTransactionsByDateRange(startDate: Long, endDate: Long): Flow<List<TransactionEntity>>

    fun getTotalUdharAllCustomers(): Flow<Double?>

    suspend fun insertTransaction(transaction: TransactionEntity): Long

    suspend fun deleteTransaction(transaction: TransactionEntity)

}