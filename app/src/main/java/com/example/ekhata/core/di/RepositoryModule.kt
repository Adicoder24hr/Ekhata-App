package com.example.ekhata.core.di

import com.example.ekhata.features.customer.data.local.dao.CustomerDao
import com.example.ekhata.data.local.dao.TransactionDao
import com.example.ekhata.features.customer.data.repository.CustomerRepositoryImpl
import com.example.ekhata.repository.impl.TransactionRepositoryImpl
import com.example.ekhata.features.customer.domain.repository.CustomerRepository
import com.example.ekhata.repository.repo.TransactionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent:: class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideCustomerRepository(
        customerDao: CustomerDao
    ): CustomerRepository{
        return CustomerRepositoryImpl(customerDao)
    }

    @Provides
    @Singleton
    fun provideTransactionRepository(
        transactionDao: TransactionDao
    ): TransactionRepository{
        return TransactionRepositoryImpl(transactionDao)
    }
}