package com.example.ekhata.repository.repo

import com.example.ekhata.data.local.entity.CustomerEntity
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {

    fun getAllCustomers(): Flow<List<CustomerEntity>>

    fun getCustomerById(customerId: Int): Flow<CustomerEntity>

    fun searchCustomers(query: String): Flow<List<CustomerEntity>>

    fun getCustomersWithPendingUdhar(): Flow<List<CustomerEntity>>

    suspend fun insertCustomer(customer: CustomerEntity): Long

    suspend fun deleteCustomer(customer: CustomerEntity)

    suspend fun updateCustomer(customer: CustomerEntity)

    suspend fun updatePendingAmount(amount: Double, customerId: Int)

}