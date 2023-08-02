package com.example.roomexample.data.datasource.local.repository

import android.app.Application
import com.example.roomexample.data.datasource.local.model.Contact
import com.example.roomexample.data.datasource.local.services.ContactDao
import com.example.roomexample.data.datasource.local.services.ContactDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class Repository @Inject constructor(private val dao: ContactDao) {
    suspend fun insertContact(contact: Contact) = dao.insertContact(contact)

    suspend fun deleteContact(contact: Contact) = dao.deleteContact(contact)

    fun getContact(): Flow<List<Contact>> = dao.getContact()

    fun getContactById(id:Int) : Flow<Contact> = dao.getContactById(id)

}