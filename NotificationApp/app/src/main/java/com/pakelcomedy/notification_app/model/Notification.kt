package com.pakelcomedy.notification_app.model

import com.google.gson.annotations.SerializedName

data class Notification(
    @SerializedName("id") val id: Int,
    @SerializedName("message") val message: String,
    @SerializedName("created_at") val createdAt: String
)

data class NotificationsResponse(
    @SerializedName("data") val notifications: List<Notification> // Using "data" from API response
)