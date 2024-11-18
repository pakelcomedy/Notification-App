package com.pakelcomedy.notification_app.api

import com.pakelcomedy.notification_app.model.NotificationsResponse
import retrofit2.http.GET

interface ApiService {

    @GET("get_notifications.php")
    suspend fun getNotifications(): NotificationsResponse
}
