package com.example.roomexample.data.datasource.local.services

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.roomexample.data.datasource.local.model.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    // insert contact to table
    @Upsert
    suspend fun insertContact(contact:Contact)

    // delete Contact from table
    @Delete
    suspend fun deleteContact(contact: Contact)

    // get Contact from table order by first name
    @Query("select * from contacts")
    fun getContact() : Flow<List<Contact>>

    // get Contact from table by id
    @Query("select * from contacts where id = :id")
    fun getContactById(id : Int) : Flow<Contact>
}