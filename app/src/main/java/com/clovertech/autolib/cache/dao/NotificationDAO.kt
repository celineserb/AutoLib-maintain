package com.clovertech.autolib.cache.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.clovertech.autolib.model.Notification

@Dao
interface NotificationDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNotification(notification: Notification)

    @Query("SELECT * FROM Notifications")
    fun getAllNotifications(): List<Notification>


}