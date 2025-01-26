package com.example.tms.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tms.R
import com.example.tms.models.Request

class RequestAdapter(
    private var requests: List<Request>,
    private val onRequestClick: (Request) -> Unit
) : RecyclerView.Adapter<RequestAdapter.RequestViewHolder>() {

    class RequestViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val description: TextView = view.findViewById(R.id.requestDescription)
        val date: TextView = view.findViewById(R.id.requestDate)
        val status: TextView = view.findViewById(R.id.requestStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_request, parent, false)
        return RequestViewHolder(view)
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        val request = requests[position]
        holder.description.text = request.description
        holder.date.text = request.date
        holder.status.text = request.status
        
        holder.itemView.setOnClickListener {
            onRequestClick(request)
        }
    }

    override fun getItemCount() = requests.size

    fun updateRequests(newRequests: List<Request>) {
        requests = newRequests
        notifyDataSetChanged()
    }
} 