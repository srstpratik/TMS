package com.example.tms.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tms.R
import com.example.tms.models.TransportRequest
import com.google.android.material.chip.Chip

class TransportRequestAdapter(
    private val onRequestClick: (TransportRequest) -> Unit,
    private val onAcceptClick: ((TransportRequest) -> Unit)? = null
) : ListAdapter<TransportRequest, TransportRequestAdapter.ViewHolder>(RequestDiffCallback()) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val companyNameTextView: TextView = view.findViewById(R.id.companyNameTextView)
        val requestIdTextView: TextView = view.findViewById(R.id.requestIdTextView)
        val productDetailsTextView: TextView = view.findViewById(R.id.productDetailsTextView)
        val routeDetailsTextView: TextView = view.findViewById(R.id.routeDetailsTextView)
        val weightChip: Chip = view.findViewById(R.id.weightChip)
        val acceptButton: Button = view.findViewById(R.id.acceptButton)
        val statusTextView: TextView = view.findViewById(R.id.statusTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transport_request, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val request = getItem(position)
        
        holder.companyNameTextView.text = request.companyName
        holder.requestIdTextView.text = "ID: ${request.id}"
        holder.productDetailsTextView.text = request.productDetails
        holder.routeDetailsTextView.text = "${request.pickupLocation} → ${request.deliveryLocation}"
        holder.weightChip.text = "${request.weight} tons"
        
        // Show/hide accept button based on request status and callback
        holder.acceptButton.visibility = if (request.status == "PENDING" && onAcceptClick != null) {
            View.VISIBLE
        } else {
            View.GONE
        }
        holder.acceptButton.setOnClickListener { onAcceptClick?.invoke(request) }
        
        // Show status with appropriate styling
        when (request.status) {
            "PENDING" -> {
                holder.statusTextView.text = "Status: Pending"
                holder.statusTextView.setTextColor(holder.itemView.context.getColor(android.R.color.holo_orange_dark))
            }
            "ACCEPTED" -> {
                holder.statusTextView.text = "Status: Accepted by ${request.truckOwnerId}"
                holder.statusTextView.setTextColor(holder.itemView.context.getColor(android.R.color.holo_green_dark))
            }
            "IN_TRANSIT" -> {
                holder.statusTextView.text = "Status: In Transit"
                holder.statusTextView.setTextColor(holder.itemView.context.getColor(android.R.color.holo_blue_dark))
            }
            "COMPLETED" -> {
                holder.statusTextView.text = "Status: Completed"
                holder.statusTextView.setTextColor(holder.itemView.context.getColor(android.R.color.darker_gray))
            }
        }
        holder.statusTextView.visibility = View.VISIBLE

        holder.itemView.setOnClickListener { onRequestClick(request) }
    }
}

class RequestDiffCallback : DiffUtil.ItemCallback<TransportRequest>() {
    override fun areItemsTheSame(oldItem: TransportRequest, newItem: TransportRequest): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TransportRequest, newItem: TransportRequest): Boolean {
        return oldItem == newItem
    }
} 