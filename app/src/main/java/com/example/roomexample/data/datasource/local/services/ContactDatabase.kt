package com.example.roomexample.data.datasource.local.services

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomexample.data.datasource.local.model.Contact

@Database(
    entities = [Contact::class],
    version = 1,
    exportSchema = false
)
abstract class ContactDatabase : RoomDatabase() {

    abstract val dao : ContactDao
    companion object {
        private val contactDatabase : ContactDatabase? = null

        fun getInstance(context: Context) : ContactDatabase {
            if (contactDatabase != null){
                return contactDatabase
            }else {
                synchronized(this) {
                    return Room.databaseBuilder(
                        context.applicationContext,
                        ContactDatabase::class.java,
                        "Contact_Database"
                    ).build()
                }
            }
        }
    }
}