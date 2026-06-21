package com.example.ekhata.features.customer.data.repository

import com.example.ekhata.core.network.NetworkResult
import com.example.ekhata.features.customer.data.local.dao.CustomerDao
import com.example.ekhata.features.customer.data.local.entity.CustomerEntity
import com.example.ekhata.features.customer.data.mapper.toDomain
import com.example.ekhata.features.customer.data.mapper.toEntity
import com.example.ekhata.features.customer.domain.model.Customer
import com.example.ekhata.features.customer.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CustomerRepositoryImpl @Inject constructor(private val customerDao: CustomerDao) :
    CustomerRepository {
    override fun getAllCustomers(): Flow<NetworkResult<List<Customer>>> =
        customerDao.getAllCustomers()
            .map { customerEntities ->
                val customers = customerEntities.map {
                    it.toDomain()
                }

                NetworkResult.Success(customers)
            }

    override fun getCustomerById(customerId: Int): Flow<NetworkResult<Customer>> =
        customerDao.getCustomerById(customerId)
            .map { customerById ->
                val customer = customerById.toDomain()

                NetworkResult.Success(customer)
            }

    override fun searchCustomers(query: String): Flow<NetworkResult<List<Customer>>> =
        customerDao.searchCustomer(query)
            .map {
                search ->
                val searchedCustomers = search.map {
                    it.toDomain()
                }

                NetworkResult.Success(searchedCustomers)
            }

    override fun getCustomersWithPendingUdhar(): Flow<NetworkResult<List<Customer>>> =
        customerDao.getCustomersWithPendingUdhar()
            .map {
                customersWithPendingUdhar ->
                val udharCustomers = customersWithPendingUdhar.map {
                    it.toDomain()
                }

                NetworkResult.Success(udharCustomers)
            }

    override suspend fun insertCustomer(customer: Customer): Long {
        return customerDao.insertCustomer(customer.toEntity())
    }

    override suspend fun deleteCustomer(customer: Customer) {
        return customerDao.deleteCustomer(customer.toEntity())
    }

    override suspend fun updateCustomer(customer: Customer) {
        return customerDao.updateCustomer(customer.toEntity())
    }

    override suspend fun updatePendingAmount(amount: Double, customerId: Int) {
       return customerDao.updatePendingAmount(amount = amount, customerId = customerId)
    }
}