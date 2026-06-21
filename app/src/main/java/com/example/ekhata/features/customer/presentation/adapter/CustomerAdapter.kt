package com.example.ekhata.features.customer.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ekhata.databinding.CustomerListItemBinding
import com.example.ekhata.features.customer.domain.model.Customer
import com.example.ekhata.util.AppUtil.DateUtils

class CustomerAdapter: ListAdapter<Customer, CustomerAdapter.CustomerViewHolder>(DiffCallBack()) {

    inner class CustomerViewHolder(private val binding: CustomerListItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(customer: Customer){
            binding.customer = customer
//            binding.LastDate.text = "Last: ${DateUtils.getTimeAgo(customer.createdAt)}"
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val binding = CustomerListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CustomerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallBack : DiffUtil.ItemCallback<Customer>(){
        override fun areItemsTheSame(oldItem: Customer, newItem: Customer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Customer, newItem: Customer): Boolean {
            return oldItem == newItem
        }
    }

}