package com.example.login.presentation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.navigation.NavHostController
import com.example.login.domain.entities.Reservation
import com.example.login.isConnected
import com.example.login.presentation.viewmodels.ReservationsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateReservationView(
    navController: NavHostController,
    reservationsViewModel: ReservationsViewModel
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var name by remember {
            mutableStateOf("")
        }

        var duration by remember {
            mutableStateOf("")
        }

        Text(text = "Auth Screen")
        TextField(
            value = name,
            onValueChange = {
                name = it
            },
        )
        TextField(
            value = duration,
            onValueChange = {
                duration = it
            },
        )
        Button(onClick = {
            val reservation =
                Reservation(name = name, duration = duration.toInt(), reservationDate = Date(2020, 12, 23))
            reservationsViewModel.createReservation(reservation)
            navController.navigate(Router.ReservationsScreen.route)
        }) {
            Text(text = "Create")
        }
    }
}


