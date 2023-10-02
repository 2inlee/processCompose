package com.wevement.myapplication


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import java.io.*

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "first",
            ) {
                composable("first") {
                    FirstScreen(navController = navController)
                }
                composable("second") {
                    SecondScreen(navController = navController)
                }
                composable("third/{value}") { backStackEntry ->
                    ThirdScreen(navController = navController,
                        value = backStackEntry.arguments?.getString("value") ?: "",
                    )
                }
            }
        }
    }

    @Composable
    fun FirstScreen(navController: NavController) {
        val (value, setValue) = remember { mutableStateOf("") }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "First Screen")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                navController.navigate("second")
            }) {
                Text(text = "Go to second screen")
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextField(value = value, onValueChange = setValue)
            Button(onClick = {
                if (value.isNotEmpty()) {
                    navController.navigate("third/$value")
                }
            }) {
                Text(text = "Go to Third screen")
            }
        }
    }

    @Composable
    fun SecondScreen(navController: NavController) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Second Screen")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                navController.navigateUp()
            }) {
                Text(text = "Go to Back")
            }
        }
    }

    @Composable
    fun ThirdScreen(navController: NavController, value: String) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Third Screen")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = value)
            Button(onClick = {
                navController.navigateUp()
            }) {
                Text(text = "Go to Back")
            }
        }
    }
}
