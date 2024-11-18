package com.pakelcomedy.notification_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pakelcomedy.notification_app.api.ApiClient
import com.pakelcomedy.notification_app.api.ApiService
import com.pakelcomedy.notification_app.model.Notification
import com.pakelcomedy.notification_app.model.NotificationsResponse // Import your response model
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class NotificationViewModel : ViewModel() {

    private val apiService: ApiService = ApiClient.getClient().create(ApiService::class.java)

    // LiveData to hold notifications
    private val _notifications = MutableLiveData<List<Notification>>()
    val notifications: LiveData<List<Notification>> get() = _notifications

    // LiveData to hold loading status
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    // LiveData to handle errors
    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    // Function to fetch notifications
    fun fetchNotifications() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                // Make the network call and get the response object (NotificationsResponse)
                val response = apiService.getNotifications() // This is a suspend function
                _notifications.value = response.notifications // Access the notifications list from the response
            } catch (e: IOException) {
                // Handle network errors (e.g., no internet connection)
                _errorMessage.value = "Network error, please try again"
            } catch (e: HttpException) {
                // Handle server errors (e.g., 500 internal server error)
                _errorMessage.value = "Server error, please try again"
            } finally {
                // Set loading state to false after the API call completes
                _isLoading.value = false
            }
        }
    }

    // Clear error message after it has been shown
    fun clearErrorMessage() {
        _errorMessage.value = null
    }
}