package com.example.roomexample.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.roomexample.ui.screens.AddContact.AddContactScreen
import com.example.roomexample.ui.screens.MainViewModel
import com.example.roomexample.ui.screens.home.HomeScreen

@Composable
fun NavigationHost(navController: NavHostController){
    val viewModel = viewModel<MainViewModel>()
    NavHost(navController = navController , startDestination = Screen.Home){
        composable(route = Screen.Home){
            HomeScreen(navController = navController , viewModel = viewModel)
        }
        composable(route = Screen.Add){
            AddContactScreen(navController = navController , viewModel = viewModel)
        }
    }
}
