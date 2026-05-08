package com.example.ekhata.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ekhata.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionEntity): Long

    @Delete
    suspend fun deleteTransaction(transaction: TransactionEntity)

    @Query("SELECT * FROM transactions WHERE customerId = :customerId ORDER BY date DESC")
    fun getTransactionsForCustomer(customerId: Int): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions ORDER BY date DESC")
    fun getAllTransactions(): Flow<List<TransactionEntity>>

    @Query("""
        SELECT SUM(CASE WHEN  type = 'UDHAR' THEN amount ELSE -amount END)
        FROM transactions
        WHERE customerId = :customerId
    """)
    fun getTotalPendingForCustomer(customerId: Int): Flow<Double?>

    @Query("SELECT * FROM transactions WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    fun getTransactionsByDateRange(startDate: Long, endDate: Long): Flow<List<TransactionEntity>>

    @Query("SELECT SUM(CASE WHEN type = 'UDHAR' THEN amount ELSE 0 END) FROM transactions")
    fun getTotalUdharAllCustomers(): Flow<Double?>

}