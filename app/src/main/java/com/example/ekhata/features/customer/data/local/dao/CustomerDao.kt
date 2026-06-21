package com.example.ekhata.features.customer.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.ekhata.features.customer.data.local.entity.CustomerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertCustomer(customer: CustomerEntity): Long

    @Update
    suspend fun updateCustomer(customer: CustomerEntity)

    @Delete
    suspend fun deleteCustomer(customer: CustomerEntity)

    @Query("SELECT * FROM customers ORDER BY name ASC")
    fun getAllCustomers(): Flow<List<CustomerEntity>>

    @Query("SELECT * FROM customers WHERE id = :customerId")
    fun getCustomerById(customerId: Int): Flow<CustomerEntity>

    @Query("SELECT * FROM customers WHERE name LIKE '%' || :searchQuery || '%'")
    fun searchCustomer(searchQuery: String): Flow<List<CustomerEntity>>

    @Query("SELECT * FROM customers WHERE totalPendingAmount > 0 ORDER BY totalPendingAmount DESC")
    fun getCustomersWithPendingUdhar(): Flow<List<CustomerEntity>>

    @Query("UPDATE customers SET totalPendingAmount = totalPendingAmount + :amount WHERE id = :customerId")
    suspend fun updatePendingAmount(amount: Double, customerId: Int)

}