package com.example.roomexample.ui.screens.AddContact

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
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
fun AddContactScreen(viewModel: MainViewModel , navController: NavController) {
    val firstName by viewModel.firstName.collectAsState()
    val lastName by viewModel.lastName.collectAsState()
    val phoneNumber by viewModel.phoneNumber.collectAsState()
    val context = LocalContext.current
    Column(Modifier.wrapContentSize(align = Alignment.TopCenter).padding(20.dp)) {
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
            colors = TextFieldDefaults.outlinedTextFieldColors()
        )
        Spacer(modifier = Modifier.height(5.dp))
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
            colors = TextFieldDefaults.outlinedTextFieldColors()
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextField(
            value = phoneNumber,
            onValueChange = {
                viewModel.updateInPhoneNumber(it)
            },
            textStyle = MaterialTheme.typography.titleSmall,
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Person, contentDescription = null)
            },
            label = {
                Text(text = "Phone Number")
            },
            colors = TextFieldDefaults.outlinedTextFieldColors()
        )
        Spacer(modifier = Modifier.height(5.dp))
        ElevatedButton(onClick = {
            if (firstName.isNotEmpty() && lastName.isNotEmpty() && phoneNumber.isNotEmpty()) {
                val contact = Contact(
                    firstName = firstName,
                    lastName = lastName,
                    phoneNumber = phoneNumber
                )
                viewModel.insertContact(contact)
                Toast.makeText(context, "Add Contact success", Toast.LENGTH_SHORT).show()
                viewModel.getContactByFirstName()
                navController.navigateUp()
            }
        }) {
            Text(text = "Add Contact")
        }
    }
}