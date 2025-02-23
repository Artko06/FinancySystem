package com.example.financysystem.presentation.navigarionScreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
                onLoginSuccessNavigation = { email ->
                    navController.navigate(route = "${Screen.UserScreen.route}/$email"){
                        popUpTo(0)
                    }
                },

                onNavigateToRegisterScreen = {
                    navController.navigate(route = Screen.RegistrationScreen.route){
                        popUpTo(0)
                    }
                }
            )
        }

        composable(route = Screen.RegistrationScreen.route){
            RegistrationScreen(
                onRegistrationSuccessNavigation = { email ->
                    navController.navigate(route = "${Screen.UserScreen.route}/$email"){
                        popUpTo(0)
                    }
                },

                onNavigateToLoginScreen = {
                    navController.navigate(route = Screen.LoginScreen.route){
                        popUpTo(0)
                    }
                }
            )
        }

        composable(
            route = "${Screen.UserScreen.route}/{userEmail}",
            arguments = listOf(navArgument("userEmail") { type = NavType.StringType })

        ){ getArg ->
            val userEmail = getArg.arguments?.getString("userEmail")!!
            UserScreen(userEmail = userEmail)
        }
    }
}