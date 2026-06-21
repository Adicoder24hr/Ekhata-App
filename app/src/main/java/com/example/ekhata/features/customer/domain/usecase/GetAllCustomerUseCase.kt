package com.example.ekhata.features.customer.domain.usecase

import com.example.ekhata.core.network.NetworkResult
import com.example.ekhata.features.customer.domain.model.Customer
import com.example.ekhata.features.customer.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCustomerUseCase @Inject constructor(private val customerRepository: CustomerRepository) {

    operator fun invoke(): Flow<NetworkResult<List<Customer>>>{
        return customerRepository.getAllCustomers()
    }

}