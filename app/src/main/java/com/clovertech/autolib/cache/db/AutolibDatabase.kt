package com.clovertech.autolib.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.clovertech.autolib.cache.converter.Converters
import com.clovertech.autolib.cache.dao.*
import com.clovertech.autolib.model.*
import com.clovertech.autolib.model.Notification

@Database(
    entities = [AuthUser::class, Agent::class
        , Step::class, Task::class, TaskModel::class
        , Materiel::class, Panne::class, Notification::class],
    version = 1,
    exportSchema = false)

@TypeConverters(Converters::class)
abstract class AutolibDatabase : RoomDatabase() {
    abstract fun authDao(): AuthDAO
    abstract fun taskDao(): TaskDAO
    abstract fun profileDao(): ProfileDAO
    abstract fun stepDao(): StepDAO
    abstract fun taskModelDao(): TaskModelDAO
    abstract fun notificationDao(): NotificationDAO
    abstract fun panneDao(): PanneDAO



    companion object {

        @Volatile
        private var INSTANCE: AutolibDatabase? = null

        fun getDatabaseClient(context: Context): AutolibDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, AutolibDatabase::class.java, "cache_db")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }
}