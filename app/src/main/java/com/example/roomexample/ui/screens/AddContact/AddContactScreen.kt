package com.example.roomexample.ui.screens.AddContact

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.roomexample.data.datasource.local.model.Contact
import com.example.roomexample.ui.screens.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactScreen(viewModel: MainViewModel, navController: NavController, id: String) {
    val firstName by viewModel.firstName.collectAsState()
    val lastName by viewModel.lastName.collectAsState()
    val phoneNumber by viewModel.phoneNumber.collectAsState()
    val contact by viewModel.contact.collectAsState()
    val context = LocalContext.current
    val textButton: String
    if (id.isNotEmpty()) {
        viewModel.getContactById(id)
        textButton = "Update Contact"
        LaunchedEffect(key1 = Unit) {
            viewModel.updateInFirstName(contact.firstName)
            viewModel.updateInLastName(contact.lastName)
            viewModel.updateInPhoneNumber(contact.phoneNumber)
        }
    } else {
        LaunchedEffect(key1 = Unit) {
            viewModel.updateInFirstName("")
            viewModel.updateInLastName("")
            viewModel.updateInPhoneNumber("")
        }
        textButton = "Add Contact"
    }
    Column(
        Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.TopCenter)
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
    ) {
        TextField(
            value = firstName,
            onValueChange = {
                viewModel.updateInFirstName(it)
            },
            textStyle = MaterialTheme.typography.titleSmall,
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Person, contentDescription = null)
            },
            label = {
                Text(text = "First Name")
            },
            colors = TextFieldDefaults.textFieldColors(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = lastName,
            onValueChange = {
                viewModel.updateInLastName(it)
            },
            textStyle = MaterialTheme.typography.titleSmall,
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Person, contentDescription = null)
            },
            label = {
                Text(text = "Last Name")
            },
            colors = TextFieldDefaults.textFieldColors(),
            modifier = Modifier.fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = phoneNumber,
            onValueChange = {
                viewModel.updateInPhoneNumber(it)
            },
            textStyle = MaterialTheme.typography.titleSmall,
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Phone, contentDescription = null)
            },
            label = {
                Text(text = "Phone Number")
            },
            colors = TextFieldDefaults.textFieldColors(),
            modifier = Modifier.fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(8.dp))
        ElevatedButton(
            onClick = {
                if (firstName.isNotEmpty() && lastName.isNotEmpty() && phoneNumber.isNotEmpty()) {
                    if (textButton == "Update Contact") {
                        val contactMe = Contact(
                            id = contact.id,
                            firstName = firstName,
                            lastName = lastName,
                            phoneNumber = phoneNumber
                        )
                        viewModel.insertContact(contactMe)
                    } else {
                        val contactMe = Contact(
                            firstName = firstName,
                            lastName = lastName,
                            phoneNumber = phoneNumber
                        )
                        viewModel.insertContact(contactMe)
                        Toast.makeText(context, "Add Contact success", Toast.LENGTH_SHORT).show()
                    }
                    navController.navigateUp()
                } else {
                    Toast.makeText(context, "Check of Fields", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = textButton)
        }
    }
}