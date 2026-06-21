package com.example.ekhata.features.customer.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ekhata.core.network.NetworkResult
import com.example.ekhata.features.customer.domain.dashboard.DashboardData
import com.example.ekhata.features.customer.domain.model.Customer
import com.example.ekhata.features.customer.domain.usecase.GetAllCustomerUseCase
import com.example.ekhata.features.customer.presentation.state.CustomerUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(private val getAllCustomerUseCase: GetAllCustomerUseCase) : ViewModel(){
    private val _customerState = MutableStateFlow<CustomerUiState>(CustomerUiState.Idle)

    val customerState: StateFlow<CustomerUiState> = _customerState.asStateFlow()

    fun getAllCustomers() {
        viewModelScope.launch {

            _customerState.value = CustomerUiState.Loading

            getAllCustomerUseCase().collect {
                result ->
                when(result){
                    is NetworkResult.Success -> {
                        val customers = result.data

                        Log.d("Dashboard", "Total customers = ${customers.size}")

                        customers.forEach {
                            Log.d(
                                "Dashboard",
                                "${it.name} -> ${it.totalPendingAmount}"
                            )
                        }

                        val totalUdhar = customers.sumOf { it.totalPendingAmount }

                        val totalCustomers = customers.size

                        val pendingCustomers = customers

                        val receivedToday = 0.0

                        val dashboard = DashboardData(
                            totalUdhar = totalUdhar,
                            receivedToday = receivedToday,
                            totalCustomers = totalCustomers,
                            pendingCustomers = pendingCustomers
                        )

                        _customerState.value = CustomerUiState.Success(dashboard)
                    }

                    is NetworkResult.Error -> {
                        _customerState.value = CustomerUiState.Error(result.message)
                    }

                    is NetworkResult.Loading -> {
                        _customerState.value = CustomerUiState.Loading
                    }
                }
            }
        }
    }
}