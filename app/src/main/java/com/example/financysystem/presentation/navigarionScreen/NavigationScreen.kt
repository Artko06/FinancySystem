package com.example.financysystem.presentation.navigarionScreen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationScreen(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ){
        composable(route = Screen.LoginScreen.route){
//            LoginScreen(navController)
        }
        composable(route = Screen.RegistrationScreen.route){
//            RegistrationScreen(navController)
        }
    }
}