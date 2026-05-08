package com.example.ekhata.repository.impl

import com.example.ekhata.data.local.dao.CustomerDao
import com.example.ekhata.repository.repo.CustomerRepository

class CustomerRepositoryImpl(val customerDao: CustomerDao) : CustomerRepository {
}