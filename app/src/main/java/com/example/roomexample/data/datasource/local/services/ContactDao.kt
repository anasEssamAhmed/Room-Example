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
    @Query("select * from contacts order by firstName ASC")
    suspend fun getContactByFirstName() : List<Contact>

    // get Contact from table order by last name
    @Query("select * from contacts order by lastName ASC")
    suspend fun getContactByLastName() : List<Contact>

    // get Contact from table order by phone number
    @Query("select * from contacts order by phoneNumber ASC")
    suspend fun getContactByPhoneNumber() : List<Contact>
}