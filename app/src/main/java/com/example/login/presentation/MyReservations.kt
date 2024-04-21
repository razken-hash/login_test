package com.example.login.presentation

import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

@Composable
fun ReservationView(
    navController: NavHostController,
    reservationsViewModel: ReservationsViewModel
) {
    val context = LocalContext.current

    LaunchedEffect(1) {
        CoroutineScope(Dispatchers.IO).launch {
            reservationsViewModel.getAllReservations()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            isConnected = false
            val prefs =
                context.getSharedPreferences(
                    "prefs", Context.MODE_PRIVATE
                )

            prefs.edit {
                putBoolean(
                    "isConnected", false
                )
            }
            navController.navigateUp()
        }) {
            Text(text = "Logout")
        }

        Button(onClick = {
            reservationsViewModel.getAllReservations()
        }) {
            Text(text = "Get Reservations")
        }

        Button(onClick = {
            navController.navigate(Router.CreateReservationsScreen.route)
//            val reservation =
//                Reservation(name = "Hi", duration = 2, reservationDate = Date(2020, 12, 23))
//            reservationsViewModel.createReservation(reservation)
//            reservationsViewModel.getAllReservations()
        }) {
            Text(text = "Create Reservation")
        }
        Text(text = "Reservation Screen")

//        val scroller = rememberScrollState()
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .verticalScroll(scroller)
//        ) {
//            for (i in 1..reservationsViewModel.allReservations.value.size) {
////                val name = reservationsViewModel.allReservations.value.get(i).name
//                Text(
//                    text = "Res $i",
//                    modifier = Modifier
//                        .padding(5.dp, 5.dp)
//                        .fillMaxWidth()
//                        .border(
//                            1.dp, Color.Black, shape = RoundedCornerShape(size = 5.dp)
//                        )
//                        .padding(20.dp, 10.dp)
//                )
//            }
//        }

        LazyColumn() {
            items(reservationsViewModel.allReservations.value) {
                Text(
                    text = "Res ${it.name}  -- ${it.duration} -- ${it.reservationDate}",
                    modifier = Modifier
                        .padding(5.dp, 5.dp)
                        .fillMaxWidth()
                        .border(
                            1.dp, Color.Black, shape = RoundedCornerShape(size = 5.dp)
                        )
                        .padding(20.dp, 10.dp)
                )
            }
        }

    }
}
