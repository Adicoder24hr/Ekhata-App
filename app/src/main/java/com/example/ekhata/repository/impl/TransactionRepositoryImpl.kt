package com.example.ekhata.repository.impl

import com.example.ekhata.data.local.dao.TransactionDao
import com.example.ekhata.repository.repo.TransactionRepository

class TransactionRepositoryImpl(val transactionDao: TransactionDao): TransactionRepository {
}