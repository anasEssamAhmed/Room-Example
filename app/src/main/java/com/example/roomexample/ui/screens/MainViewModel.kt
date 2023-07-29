package com.example.roomexample.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomexample.data.datasource.local.model.Contact
import com.example.roomexample.data.datasource.local.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository = Repository.getInstance()) : ViewModel() {

    // first name
    private val _firstName = MutableStateFlow("")
    val firstName = _firstName.asStateFlow()

    // last Name
    private val _lastName = MutableStateFlow("")
    val lastName = _lastName.asStateFlow()

    // phone Number
    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber = _phoneNumber.asStateFlow()

    fun insertContact(contact: Contact){
        viewModelScope.launch {
            repository.insertContact(contact)
        }
    }

    fun deleteContact(contact: Contact){
        viewModelScope.launch {
            repository.deleteContact(contact)
        }
    }
    private val _listOfContact = MutableStateFlow<List<Contact>>(listOf())
    val listOfContact = _listOfContact.asStateFlow()
    fun getContactByFirstName(){
        viewModelScope.launch {
            val listOfContact = repository.getContactByFirstName()
             listOfContact.collect{
             _listOfContact.value = it
            }
        }
    }

    fun getContactByLastName(){
        viewModelScope.launch {
            val listOfContact = repository.getContactByLastName()
            listOfContact.collect{
                _listOfContact.value = it
            }
        }
    }

    fun getContactByPhoneNumber(){
        viewModelScope.launch {
            val listOfContact = repository.getContactByPhoneNumber()
            listOfContact.collect{
                _listOfContact.value = it
            }
        }
    }

    // update in first name
    fun updateInFirstName(value : String){
        _firstName.value = value
    }

    // update in last Name
    fun updateInLastName(value: String){
        _lastName.value = value
    }

    // update in phone number
    fun updateInPhoneNumber(value: String){
        _phoneNumber.value = value
    }
}