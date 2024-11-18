package com.pakelcomedy.notification_app.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pakelcomedy.notification_app.R
import com.pakelcomedy.notification_app.model.Notification

class NotificationAdapter(private var notifications: List<Notification>) :
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    // This method is used to create a new view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        // Inflate the layout for individual notification items
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notification_item, parent, false)
        return NotificationViewHolder(view)
    }

    // This method binds the data (notification) to the view holder's views
    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notifications[position]

        // Set the message and timestamp for each notification
        holder.messageTextView.text = notification.message
        holder.timestampTextView.text = notification.createdAt // You can format this date if needed
    }

    // This method returns the size of the list
    override fun getItemCount(): Int = notifications.size

    // Method to update the notifications list in the adapter
    fun updateNotifications(newNotifications: List<Notification>) {
        notifications = newNotifications
        notifyDataSetChanged()  // Notify the adapter that data has changed
    }

    // ViewHolder to hold references to the views in each notification item
    inner class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageTextView: TextView = itemView.findViewById(R.id.notificationMessage)
        val timestampTextView: TextView = itemView.findViewById(R.id.notificationTimestamp)
    }
}