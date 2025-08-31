package com.example.testone.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testone.prasentation.view.LoadLogin
import com.example.testone.prasentation.view.LoadUserList
import com.example.testone.prasentation.view.register.RegisterState
import com.example.testone.prasentation.view.register.RegistrationScreen
import com.example.testone.prasentation.viewModel.RegistrationViewModel

sealed class NavDestinations(val route: String) {
    object Register : NavDestinations("register")
    object Login : NavDestinations("login")
    object UserList : NavDestinations("userList")
}

@Composable
fun AppNavigator(navController: NavHostController, viewModel: RegistrationViewModel = hiltViewModel()) {
    val registerState = viewModel.uiState.collectAsState()
    val startDestination = when (registerState.value) {
        RegisterState.Login -> NavDestinations.Login.route
        else -> NavDestinations.Register.route
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(NavDestinations.Register.route) {
            RegistrationScreen()
        }

        composable(NavDestinations.Login.route) {
            LoadLogin()
        }
        composable(NavDestinations.UserList.route) {
            LoadUserList()
        }
    }
}
