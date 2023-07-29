package com.example.roomexample.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.roomexample.navigation.Screen
import com.example.roomexample.ui.screens.MainViewModel


@Composable
fun CardOfContact(firstName: String, lastName: String, phoneNumber: String) {
    Row(
        Modifier
            .wrapContentSize(Alignment.TopCenter)
            .fillMaxWidth()
            .padding(10.dp)
            .background(color = Color(0xffFFC269), RoundedCornerShape(8.dp))
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Text(
                text = "$firstName $lastName",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = phoneNumber, style = MaterialTheme.typography.titleMedium)
        }
        Icon(imageVector = Icons.Filled.Delete, contentDescription = null, Modifier.clickable {
            // delete contact
        })

    }
}

@Composable
fun MyFloatingActionButton(navController: NavController) {
    Box {
        Icon(imageVector = Icons.Filled.AddCircle, contentDescription = null,
            Modifier
                .clickable {
                    navController.navigate(Screen.Add)
                }
                .align(Alignment.BottomEnd)
                .padding(20.dp)
                .size(50.dp), tint = Color(0xff0EEB61))
    }
}

@Composable
fun HomeScreen(viewModel: MainViewModel , navController: NavController) {
    val listOfContact by viewModel.listOfContact.collectAsState()
    LazyColumn() {
        items(listOfContact) {
            CardOfContact(
                firstName = it.firstName,
                lastName = it.lastName,
                phoneNumber = it.phoneNumber
            )
        }
    }
    MyFloatingActionButton(navController = navController)
}