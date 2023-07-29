package com.example.roomexample.data.datasource.local.repository

import android.app.Application
import com.example.roomexample.data.datasource.local.model.Contact
import com.example.roomexample.data.datasource.local.services.ContactDao
import com.example.roomexample.data.datasource.local.services.ContactDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class Repository(private val dao: ContactDao) {

    companion object {
        private var instance : Repository? = null
        private val application : Application = Application()
        private val dao = ContactDatabase.getInstance(context = application).dao
        fun getInstance() : Repository {
            return instance ?: synchronized(this) {
                instance ?: Repository(dao).also { instance = it}
            }
        }
    }

    suspend fun insertContact(contact : Contact) {
        dao.insertContact(contact)
    }

    suspend fun deleteContact(contact: Contact){
        dao.deleteContact(contact)
    }

    suspend fun getContactByFirstName() : Flow<List<Contact>> {
        val contact = MutableStateFlow(dao.getContactByFirstName())
        return contact
    }

    suspend fun getContactByLastName() : Flow<List<Contact>> {
        val contact = MutableStateFlow(dao.getContactByLastName())
        return contact
    }

    suspend fun getContactByPhoneNumber() : Flow<List<Contact>> {
        val contact = MutableStateFlow(dao.getContactByPhoneNumber())
        return contact
    }

}