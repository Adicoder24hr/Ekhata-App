package com.example.ekhata.features.customer.domain.repository

import com.example.ekhata.core.network.NetworkResult
import com.example.ekhata.features.customer.data.local.entity.CustomerEntity
import com.example.ekhata.features.customer.domain.model.Customer
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {

    fun getAllCustomers(): Flow<NetworkResult<List<Customer>>>

    fun getCustomerById(customerId: Int): Flow<NetworkResult<Customer>>

    fun searchCustomers(query: String): Flow<NetworkResult<List<Customer>>>

    fun getCustomersWithPendingUdhar(): Flow<NetworkResult<List<Customer>>>

    suspend fun insertCustomer(customer: Customer): Long

    suspend fun deleteCustomer(customer: Customer)

    suspend fun updateCustomer(customer: Customer)

    suspend fun updatePendingAmount(amount: Double, customerId: Int)

}