package com.example.ekhata.features.customer.domain.usecase

import com.example.ekhata.features.customer.domain.model.Customer
import com.example.ekhata.features.customer.domain.repository.CustomerRepository
import javax.inject.Inject

class InsertCustomerUseCase @Inject constructor(
    private val repository: CustomerRepository
) {

    suspend operator fun invoke(customer: Customer) = repository.insertCustomer(customer)
}