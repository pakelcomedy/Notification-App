package com.pakelcomedy.notification_app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pakelcomedy.notification_app.R
import com.pakelcomedy.notification_app.ui.adapters.NotificationAdapter
import com.pakelcomedy.notification_app.viewmodel.NotificationViewModel

class NotificationFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: NotificationAdapter
    private val notificationViewModel: NotificationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notification, container, false)

        // Initialize views
        initViews(view)

        // Set up RecyclerView
        setupRecyclerView()

        // Observe ViewModel
        observeViewModel()

        // Load notifications
        notificationViewModel.fetchNotifications()

        return view
    }

    // Initialize views
    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.recyclerViewNotifications)
        progressBar = view.findViewById(R.id.loadingIndicator)
    }

    // Set up RecyclerView
    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context ?: requireActivity())
        adapter = NotificationAdapter(emptyList()) // Initially empty list
        recyclerView.adapter = adapter
    }

    // Observe ViewModel for updates
    private fun observeViewModel() {
        // Observe notifications
        notificationViewModel.notifications.observe(viewLifecycleOwner, Observer { notifications ->
            if (notifications.isNotEmpty()) {
                adapter.updateNotifications(notifications)
            } else {
                // Show a message if there are no notifications
                Toast.makeText(context, getString(R.string.no_notifications), Toast.LENGTH_SHORT).show()
                adapter.updateNotifications(emptyList())
            }
        })

        // Observe loading state
        notificationViewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        // Observe error messages
        notificationViewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            errorMessage?.let {
                Toast.makeText(context ?: requireActivity(), it, Toast.LENGTH_SHORT).show()
                notificationViewModel.clearErrorMessage()
            }
        })
    }
}