package com.example.testone.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.testone.prasentation.view.register.RegisterState
import com.example.testone.prasentation.viewModel.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // TestOneTheme {
            Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                // Greeting(innerPadding = innerPadding)
                //RegistrationScreen(innerPadding = innerPadding)
                val navController = rememberNavController()
                //collectAsStateWithLifecycle

                AppNavigator(navController)
            }
            // }
        }
    }
}
