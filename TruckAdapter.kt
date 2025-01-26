package com.example.tms.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tms.R
import com.example.tms.models.Truck
import com.google.android.material.switchmaterial.SwitchMaterial

class TruckAdapter(
    private val onAvailabilityChanged: (Truck, Boolean) -> Unit,
    private val onItemClick: (Truck) -> Unit
) : ListAdapter<Truck, TruckAdapter.ViewHolder>(TruckDiffCallback()) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val registrationNumber: TextView = view.findViewById(R.id.registrationNumber)
        val truckDetails: TextView = view.findViewById(R.id.truckDetails)
        val locationInfo: TextView = view.findViewById(R.id.locationInfo)
        val availabilitySwitch: SwitchMaterial = view.findViewById(R.id.availabilitySwitch)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_truck, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val truck = getItem(position)
        holder.registrationNumber.text = truck.registrationNumber
        holder.truckDetails.text = "${truck.truckType} - ${truck.capacity} tons"
        holder.locationInfo.text = "Location: ${truck.currentLocation ?: "Not available"}"
        
        holder.availabilitySwitch.isChecked = truck.isAvailable
        holder.availabilitySwitch.setOnCheckedChangeListener { _, isChecked ->
            onAvailabilityChanged(truck, isChecked)
        }

        holder.itemView.setOnClickListener {
            onItemClick(truck)
        }
    }
}

class TruckDiffCallback : DiffUtil.ItemCallback<Truck>() {
    override fun areItemsTheSame(oldItem: Truck, newItem: Truck): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Truck, newItem: Truck): Boolean {
        return oldItem == newItem
    }
} 