package com.example.testone.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testone.prasentation.view.LoadUserList
import com.example.testone.prasentation.view.loginViewModel.LoadLogin
import com.example.testone.prasentation.view.register.RegistrationScreen

sealed class NavDestinations(val route: String) {
    object Register : NavDestinations("register")
    object Login : NavDestinations("login")
    object UserList : NavDestinations("userList")
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    // val registerState by viewModel.uiState.collectAsState()

    /*val startDestination = when (registerState) {
        RegisterState.Login -> NavDestinations.Login.route
        else -> NavDestinations.Register.route
    }*/

    NavHost(navController = navController, startDestination = NavDestinations.Register.route) {
        composable(NavDestinations.Register.route) {
            RegistrationScreen(
                onNavLogin = {
                    navController.navigate(NavDestinations.Login.route) {
                        popUpTo(NavDestinations.Register.route) {
                            inclusive = true
                        }
                    }
                },
            )
        }

        composable(NavDestinations.Login.route) {
            LoadLogin(
                onHome = {
                    navController.navigate(NavDestinations.UserList.route) {
                        popUpTo(NavDestinations.Login.route) {
                            inclusive = true
                        }
                    }
                },
            )
        }
        composable(NavDestinations.UserList.route) {
            LoadUserList()
        }
    }
}
