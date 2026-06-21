package com.example.ekhata.core.di

import android.content.Context
import androidx.room.Room
import com.example.ekhata.features.customer.data.local.dao.CustomerDao
import com.example.ekhata.data.local.dao.TransactionDao
import com.example.ekhata.data.local.database.KhataDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideKhataDatabase(
        @ApplicationContext context: Context
    ): KhataDatabase {
        return Room.databaseBuilder(
            context,
            KhataDatabase::class.java,
            KhataDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCustomerDao(database: KhataDatabase): CustomerDao{
        return database.customerDao()
    }

    @Provides
    @Singleton
    fun provideTransactionDao(database: KhataDatabase): TransactionDao{
        return database.transactionDao()
    }
}