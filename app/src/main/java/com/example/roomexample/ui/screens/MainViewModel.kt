package com.example.roomexample.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomexample.data.datasource.local.model.Contact
import com.example.roomexample.data.datasource.local.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    // first name
    private val _firstName = MutableStateFlow("")
    val firstName = _firstName.asStateFlow()

    // last Name
    private val _lastName = MutableStateFlow("")
    val lastName = _lastName.asStateFlow()

    // phone Number
    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber = _phoneNumber.asStateFlow()

    // Insert or update in table
    fun insertContact(contact: Contact){
        viewModelScope.launch {
            repository.insertContact(contact)
        }
    }

    // delete table
    fun deleteContact(contact: Contact){
        viewModelScope.launch {
            repository.deleteContact(contact)
        }
    }
    private val _listOfContact = MutableStateFlow<List<Contact>>(listOf())
    val listOfContact = _listOfContact.asStateFlow()
    fun getContact(){
        viewModelScope.launch {
            val listOfContact = repository.getContact()
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

    // *** Get Contact From Table Using ID ***
    private val _contact = MutableStateFlow<Contact>(Contact())
    val contact = _contact.asStateFlow()

    fun getContactById(id : String){
        viewModelScope.launch {
            val idInt = Integer.parseInt(id)
            val contact = repository.getContactById(idInt)
            contact.collect{
                _contact.value = it
            }
        }
    }
}