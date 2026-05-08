package com.example.ekhata.repository.impl

import com.example.ekhata.data.local.dao.CustomerDao
import com.example.ekhata.data.local.entity.CustomerEntity
import com.example.ekhata.repository.repo.CustomerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CustomerRepositoryImpl @Inject constructor(private val customerDao: CustomerDao) : CustomerRepository {
    override fun getAllCustomers(): Flow<List<CustomerEntity>> {
        return customerDao.getAllCustomers()
    }

    override fun getCustomerById(customerId: Int): Flow<CustomerEntity> {
        return customerDao.getCustomerById(customerId)
    }

    override fun searchCustomers(query: String): Flow<List<CustomerEntity>> {
       return customerDao.searchCustomer(query)
    }

    override fun getCustomersWithPendingUdhar(): Flow<List<CustomerEntity>> {
        return getCustomersWithPendingUdhar()
    }

    override suspend fun insertCustomer(customer: CustomerEntity): Long {
        return customerDao.insertCustomer(customer)
    }

    override suspend fun deleteCustomer(customer: CustomerEntity) {
        return customerDao.deleteCustomer(customer)
    }

    override suspend fun updateCustomer(customer: CustomerEntity) {
        return customerDao.updateCustomer(customer)
    }

    override suspend fun updatePendingAmount(amount: Double, customerId: Int) {
       return customerDao.updatePendingAmount(amount = amount, customerId = customerId)
    }
}