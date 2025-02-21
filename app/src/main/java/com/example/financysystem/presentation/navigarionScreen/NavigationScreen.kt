package com.example.financysystem.presentation.navigarionScreen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.financysystem.presentation.screens.loginRegistrScreen.LoginScreen
import com.example.financysystem.presentation.screens.loginRegistrScreen.RegistrationScreen
import com.example.financysystem.presentation.screens.userScreen.UserScreen

@Composable
fun NavigationScreen(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    )
    {
        composable(route = Screen.LoginScreen.route){
            LoginScreen(
                onLoginSuccessNavigation = {
                    navController.navigate(Screen.UserScreen.route){
                        popUpTo(0)
                    }
                },

                onNavigateToRegisterScreen = {
                    navController.navigate(Screen.RegistrationScreen.route){
                        popUpTo(0)
                    }
                }
            )
        }

        composable(route = Screen.RegistrationScreen.route){
            RegistrationScreen(
                onRegistrationSuccessNavigation = {
                    navController.navigate(Screen.UserScreen.route){
                        popUpTo(0)
                    }
                },

                onNavigateToLoginScreen = {
                    navController.navigate(Screen.LoginScreen.route){
                        popUpTo(0)
                    }
                }
            )
        }

        composable(route = Screen.UserScreen.route){
            UserScreen()
        }
    }
}