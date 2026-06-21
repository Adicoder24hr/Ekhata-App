package com.example.ekhata.features.customer.presentation.ui

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.ekhata.R
import com.example.ekhata.databinding.FragmentAddCustomerBinding
import com.example.ekhata.features.customer.domain.model.Customer
import com.example.ekhata.features.customer.presentation.state.AddCustomerUiState
import com.example.ekhata.features.customer.presentation.viewmodel.AddCustomerVM
import com.example.ekhata.util.SweetAlertDialogProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddCustomerFragment : Fragment() {

    private var _binding: FragmentAddCustomerBinding? = null
    private val binding get() = _binding!!

    private var selectedImageUri: Uri? = null

    val addCustomerVm: AddCustomerVM by viewModels()

    private val sweetAlertDialog = SweetAlertDialogProvider()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddCustomerBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickListners()
        TextWatcher()
        observer()
    }

    private fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                addCustomerVm.addCustomerState.collect { state ->
                    when(state){

                        is AddCustomerUiState.Loading -> {
                            sweetAlertDialog.showProgressDialog(
                                "Saving Customer...",
                                requireContext()
                            )
                        }

                        is AddCustomerUiState.Success -> {
                            sweetAlertDialog.dismissProgressDialog()

                            sweetAlertDialog.showSuccessDialog(
                                "Customer added successfully",
                                requireContext()
                            ){
                                findNavController().navigate(R.id.dashboardFragment)
                            }
                        }

                        is AddCustomerUiState.Error -> {
                            sweetAlertDialog.dismissProgressDialog()

                            sweetAlertDialog.showErrorDialog(
                                state.message,
                                requireContext()
                            )
                        }

                        else -> {}

                    }

                }
            }
        }
    }

    private fun TextWatcher() {
        binding.etCustomerName.doAfterTextChanged {
            val name = it.toString()

            if(name.isNotEmpty()){
                binding.tvAvatarLetter.text = name.first().uppercase()
            }
        }
    }

    private fun clickListners() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnback.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnCancel.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnSubmit.setOnClickListener {
            val customer = Customer(
                name = binding.etCustomerName.text.toString(),
                phoneNumber = binding.etPhoneNumber.text.toString(),
                address = binding.etAddress.text.toString(),
                totalPendingAmount = binding.OpeningBalance.text.toString().toDoubleOrNull() ?: 0.0,
                profileImageUri = selectedImageUri?.toString() ?: ""
            )

            addCustomerVm.saveCustomer(customer)

        }

        binding.avatarContainer.setOnClickListener {
            showImagePickerDialog()
        }
    }


    private fun showImagePickerDialog(){
        val options = arrayOf(
            getString(R.string.take_photo),
            getString(R.string.choose_from_gallery),
        )

        AlertDialog.Builder(requireContext())
            .setTitle("Select Profile Photo")
            .setItems(options){ _, which ->
                when(which){
                    0 -> openCamera()

                    1-> openGallery()
                }
            }.show()
    }

    private val galleryLauncher =
        registerForActivityResult(
            ActivityResultContracts.GetContent()
        ){ uri ->
            uri?.let {
                selectedImageUri = it

                binding.ivProfile.visibility = View.VISIBLE
                binding.tvAvatarLetter.visibility = View.GONE

                binding.ivProfile.setImageURI(it)
            }
        }

    private fun openGallery() {
        galleryLauncher.launch("image/*")
    }

    private fun openCamera() {
        TODO("Not yet implemented")
    }

    /**adding a text watcher which will show the
     fist letter of the customer's name when no image is selected**/

}