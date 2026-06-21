package com.example.ekhata.features.customer.data.mapper

import com.example.ekhata.features.customer.data.local.entity.CustomerEntity
import com.example.ekhata.features.customer.domain.model.Customer

fun CustomerEntity.toDomain(): Customer{
    return Customer(
        id = id,
        name = name,
        phoneNumber = phoneNumber,
        address = address,
        totalPendingAmount = totalPendingAmount,
        createdAt = createdAt,
        fcmToken = fcmToken,
        profileImageUri = profileImageUri
    )
}

fun Customer.toEntity(): CustomerEntity{
    return CustomerEntity(
        id = id,
        name = name,
        phoneNumber = phoneNumber,
        address = address,
        totalPendingAmount = totalPendingAmount,
        createdAt = createdAt,
        fcmToken = fcmToken,
        profileImageUri = profileImageUri
    )
}