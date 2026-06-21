package com.example.ekhata.features.customer.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ekhata.R
import com.example.ekhata.databinding.FragmentCustomersBinding
import com.example.ekhata.features.customer.presentation.adapter.CustomerAdapter
import com.example.ekhata.features.customer.presentation.state.CustomerUiState
import com.example.ekhata.features.customer.presentation.viewmodel.CustomerViewModel
import com.example.ekhata.util.SweetAlertDialogProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentCustomersBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CustomerAdapter

    val viewmodel: CustomerViewModel by viewModels()

    private val sweetAlertDialog = SweetAlertDialogProvider()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCustomersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CustomerAdapter()

        binding.rvCustomers.layoutManager =
            LinearLayoutManager(requireContext())

        binding.rvCustomers.adapter = adapter

        observeState()
        clickListneres()

        viewmodel.getAllCustomers()
    }

    private fun clickListneres() {
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_addCustomerFragment)
        }
    }

    private fun observeState() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewmodel.customerState.collect {
                    state ->
                    when(state){
                        is CustomerUiState.Loading ->{
                            binding.progressBar.visibility = View.VISIBLE
                            binding.rvCustomers.visibility = View.GONE
                        }
                        is CustomerUiState.Success -> {
                            val data = state.data

                            binding.progressBar.visibility = View.GONE
                            binding.rvCustomers.visibility = View.VISIBLE

                            // 🔥 Update cards
                            binding.dashboard = data
                            binding.executePendingBindings()

                            // 🔥 Update list
                            adapter.submitList(data.pendingCustomers)

                        }

                        is CustomerUiState.Error ->{
                            binding.progressBar.visibility = View.GONE
                            binding.rvCustomers.visibility = View.GONE

                            sweetAlertDialog.showErrorDialog(getString(R.string.something_went_wrong), requireContext())
                        }

                        else -> {}
                    }
                }
            }
        }

    }


}