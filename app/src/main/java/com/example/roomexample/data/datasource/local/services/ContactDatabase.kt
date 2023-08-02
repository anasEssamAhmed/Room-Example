package com.example.roomexample.data.datasource.local.services

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomexample.data.datasource.local.model.Contact

@Database(
    entities = [Contact::class],
    version = 1,
    exportSchema = false
)
abstract class ContactDatabase : RoomDatabase() {

    abstract val dao: ContactDao

}