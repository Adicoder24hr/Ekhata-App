package com.example.ekhata.features.customer.presentation.state

interface AddCustomerUiState {

    data object Idle : AddCustomerUiState

    data object Loading : AddCustomerUiState

    data object Success : AddCustomerUiState

    data class Error(val message: String) : AddCustomerUiState

}