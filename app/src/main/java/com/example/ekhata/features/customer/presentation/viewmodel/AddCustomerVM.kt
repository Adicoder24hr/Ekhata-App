package com.example.ekhata.features.customer.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ekhata.features.customer.domain.model.Customer
import com.example.ekhata.features.customer.domain.usecase.InsertCustomerUseCase
import com.example.ekhata.features.customer.presentation.state.AddCustomerUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCustomerVM @Inject constructor(
    private val insertCustomerUseCase: InsertCustomerUseCase
): ViewModel(){

    private val _addCustomerState = MutableStateFlow<AddCustomerUiState>(AddCustomerUiState.Idle)

    val addCustomerState : StateFlow<AddCustomerUiState> = _addCustomerState.asStateFlow()

    fun saveCustomer(customer: Customer) {

        viewModelScope.launch {

            try {

                _addCustomerState.value =
                    AddCustomerUiState.Loading

                if (!validation(customer)) {
                    return@launch
                }

                Log.d("Hula", "insert customer use case executed!")

                insertCustomerUseCase(customer)

                _addCustomerState.value =
                    AddCustomerUiState.Success

            } catch (e: Exception) {

                _addCustomerState.value =
                    AddCustomerUiState.Error(
                        e.message ?: "Something went wrong"
                    )
            }
        }
    }

    fun validation(customer: Customer): Boolean{
        if (customer.name.isBlank()){
            _addCustomerState.value = AddCustomerUiState.Error("Name cannot be empty")
            return false
        }

        if (
            customer.phoneNumber.length != 10 ||
            !customer.phoneNumber.all { it.isDigit() }
        ) {
            _addCustomerState.value =
                AddCustomerUiState.Error(
                    "Enter valid phone number"
                )
            return false
        }

        if (customer.totalPendingAmount < 0 || customer.totalPendingAmount.isNaN()){
            _addCustomerState.value = AddCustomerUiState.Error("Invalid amount")
            return false
        }

        return true
    }

}