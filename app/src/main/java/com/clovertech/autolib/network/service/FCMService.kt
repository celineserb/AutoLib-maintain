package com.clovertech.autolib.network.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.edit
import com.clovertech.autolib.R
import com.clovertech.autolib.views.activities.HomeActivity
import com.clovertech.autolib.model.AgentToken
import com.clovertech.autolib.network.client.NotificationsApiClient
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FCMService: FirebaseMessagingService() {

    val TAG = "LOG TAG"
    private lateinit var prefs:SharedPreferences

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        prefs = applicationContext.getSharedPreferences("AUTOLIB_MAINTAIN", MODE_PRIVATE)
        val agentId = prefs.getInt("AGENT_ID",0)
        CoroutineScope(Dispatchers.IO).launch {
            val request = NotificationsApiClient.notificationService.postToken(AgentToken(agentId, p0))
            if (request.isSuccessful){
                Log.d(TAG, "Refreshed token: $p0")
            }
        }
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)

        Log.d(TAG, "onMessageReceived: success")

        createNotificationChannel()
        
        // Create push notification
        val intent = Intent(this, HomeActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val builder = NotificationCompat.Builder(this, getString(R.string.default_channel_id))
            .setSmallIcon(R.drawable.app_logo_jaune)
            .setContentTitle(getString(R.string.app_name))
            .setContentText(getString(R.string.notif_msg_default))
            .setContentIntent(pendingIntent)
            .setVibrate(longArrayOf(1000))
            .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            val notificationId = prefs.getInt("NOTIF_COUNT",0)
            notify(notificationId, builder.build())
            prefs.edit { putInt("NOTIF_COUNT",notificationId+1) }
        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(getString(R.string.default_channel_id), name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


}