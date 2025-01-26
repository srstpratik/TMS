package com.example.tms.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tms.R
import com.example.tms.models.Shipment
import com.google.android.material.button.MaterialButton

class ShipmentAdapter(
    private val onTrackClick: (Shipment) -> Unit
) : ListAdapter<Shipment, ShipmentAdapter.ViewHolder>(ShipmentDiffCallback()) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val shipmentId: TextView = view.findViewById(R.id.shipmentId)
        val productDetails: TextView = view.findViewById(R.id.productDetails)
        val locationInfo: TextView = view.findViewById(R.id.locationInfo)
        val statusInfo: TextView = view.findViewById(R.id.statusInfo)
        val trackButton: MaterialButton = view.findViewById(R.id.trackButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_shipment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shipment = getItem(position)
        holder.shipmentId.text = "Shipment #${shipment.id}"
        holder.productDetails.text = shipment.productDetails
        holder.locationInfo.text = "Current Location: ${shipment.currentLocation}"
        holder.statusInfo.text = "Status: ${shipment.status}"
        
        holder.trackButton.setOnClickListener {
            onTrackClick(shipment)
        }
    }
}

class ShipmentDiffCallback : DiffUtil.ItemCallback<Shipment>() {
    override fun areItemsTheSame(oldItem: Shipment, newItem: Shipment): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Shipment, newItem: Shipment): Boolean {
        return oldItem == newItem
    }
} 