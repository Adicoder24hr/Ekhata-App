package com.example.ekhata.features.customer.presentation.state

import com.example.ekhata.features.customer.domain.dashboard.DashboardData
import com.example.ekhata.features.customer.domain.model.Customer

sealed interface CustomerUiState {

    data object Idle: CustomerUiState

    data object Loading: CustomerUiState

    data class Success(val data: DashboardData): CustomerUiState

    data class Error(val message: String): CustomerUiState

}