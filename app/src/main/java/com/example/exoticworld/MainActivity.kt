package com.example.exoticworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.exoticworld.ui.theme.ExoticWorldTheme
import com.example.exoticworld.ui.screens.HomeScreen
import com.example.exoticworld.ui.screens.ProfileScreen
import com.example.exoticworld.ui.screens.CartScreen
import com.example.exoticworld.ui.screens.SettingsScreen
import com.example.exoticworld.navegation.BottomNavItem
import com.example.exoticworld.navegation.BottomBar
import com.example.exoticworld.navegation.Routes
import androidx.navigation.compose.composable
import com.example.exoticworld.ui.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExoticWorldTheme { App() }
            }
        }
    }

@Composable
fun App() {
    val navController = rememberNavController()
    val bottomItems = listOf(BottomNavItem.Home, BottomNavItem.Profile, BottomNavItem.Cart,
        BottomNavItem.Settings)

    Scaffold(
        bottomBar = { BottomBar(navController, bottomItems) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.HOME) {
                val vm: MainViewModel = viewModel()
                HomeScreen(viewModel = vm, onItemClick = { id ->
                    println("hola")
                })
            }
            composable(Routes.PROFILE) { ProfileScreen() }

            composable(Routes.CART) { CartScreen() }

            composable(Routes.SETTINGS) { SettingsScreen() }
        }
    }
}

