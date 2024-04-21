package com.example.login.presentation

sealed class Router(val route: String) {
    object AuthScreen: Router("/auth")
    object HomeScreen: Router("/home")
    object ReservationsScreen: Router("/reservation")
    object CreateReservationsScreen: Router("/create")
}