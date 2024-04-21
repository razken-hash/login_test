package com.example.login

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.login.presentation.AuthView
import com.example.login.presentation.CreateReservationView
import com.example.login.presentation.HomeView
import com.example.login.presentation.ReservationView
import com.example.login.ui.theme.LoginTheme
import com.example.login.presentation.viewmodels.ReservationsViewModel
import com.example.login.presentation.Router

var isConnected = false

class MainActivity : ComponentActivity() {

    private val reservationsViewModel: ReservationsViewModel by viewModels {
        ReservationsViewModel.Factory((application as MyApplication).reservationRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current

            val prefs =
                context.getSharedPreferences(
                    "prefs", Context.MODE_PRIVATE
                )
            isConnected = prefs.getBoolean(
                "isConnected", false
            )

            LoginTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Router.HomeScreen.route
                    ) {
                        composable(route = Router.HomeScreen.route) {
                            HomeView(navController = navController)
                        }
                        composable(route = Router.AuthScreen.route) {
                            AuthView(navController = navController)
                        }
                        composable(route = Router.ReservationsScreen.route) {
                            ReservationView(
                                navController = navController,
                                reservationsViewModel = reservationsViewModel
                            )
                        }
                        composable(route = Router.CreateReservationsScreen.route) {
                            CreateReservationView(
                                navController = navController,
                                reservationsViewModel = reservationsViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LoginTheme {
        Greeting("Android")
    }
}