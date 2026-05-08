package com.example.ekhata.repository.impl

import com.example.ekhata.data.local.dao.TransactionDao
import com.example.ekhata.data.local.entity.TransactionEntity
import com.example.ekhata.repository.repo.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(private val transactionDao: TransactionDao): TransactionRepository {
    override fun getAllTransactions(): Flow<List<TransactionEntity>> {
        return transactionDao.getAllTransactions()
    }

    override fun getTransactionsForCustomer(customerId: Int): Flow<List<TransactionEntity>> {
        return transactionDao.getTransactionsForCustomer(customerId)
    }

    override fun getTotalPendingForCustomer(customerId: Int): Flow<Double?> {
       return transactionDao.getTotalPendingForCustomer(customerId)
    }

    override fun getTransactionsByDateRange(
        startDate: Long,
        endDate: Long
    ): Flow<List<TransactionEntity>> {
        return transactionDao.getTransactionsByDateRange(startDate, endDate)
    }

    override fun getTotalUdharAllCustomers(): Flow<Double?> {
        return transactionDao.getTotalUdharAllCustomers()
    }

    override suspend fun insertTransaction(transaction: TransactionEntity): Long {
        return transactionDao.insertTransaction(transaction)
    }

    override suspend fun deleteTransaction(transaction: TransactionEntity) {
        return transactionDao.deleteTransaction(transaction)
    }
}