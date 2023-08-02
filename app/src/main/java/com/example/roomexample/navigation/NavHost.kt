package com.example.roomexample.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.roomexample.ui.screens.AddContact.AddContactScreen
import com.example.roomexample.ui.screens.MainViewModel
import com.example.roomexample.ui.screens.home.HomeScreen

@Composable
fun NavigationHost(navController: NavHostController){
    val viewModel = hiltViewModel<MainViewModel>()
    NavHost(navController = navController , startDestination = Screen.Home){
        composable(route = Screen.Home){
            HomeScreen(navController = navController , viewModel = viewModel)
        }
        composable(route = Screen.AddWithData , arguments = listOf(
            navArgument("id") {
                NavType.IntType
            }
        )){
            val id = it.arguments?.getString("id")
            AddContactScreen(navController = navController , viewModel = viewModel , id = id!!)
        }
        composable(route = Screen.Add){
            AddContactScreen(viewModel = viewModel, navController = navController, id = "")
        }
    }
}
