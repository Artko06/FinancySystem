package com.example.financysystem.presentation.navigarionScreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.domain.models.user.TypeOfUser
import com.example.financysystem.presentation.screens.loginRegistrScreen.screen.LoginScreen
import com.example.financysystem.presentation.screens.loginRegistrScreen.screen.RegistrationScreen
import com.example.financysystem.presentation.screens.userScreen.screen.AdminUserMainScreen
import com.example.financysystem.presentation.screens.userScreen.screen.ClientUserMainScreen
import com.example.financysystem.presentation.screens.userScreen.screen.CompanyUserMainScreen
import com.example.financysystem.presentation.screens.userScreen.screen.ManagerUserMainScreen
import com.example.financysystem.presentation.screens.userScreen.screen.OperatorUserMainScreen

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
                onLoginSuccessNavigation = { email, typeOfUser ->
                    when(typeOfUser){
                        TypeOfUser.AdminUser -> {
                            navController.navigate(route = "${Screen.AdminUserScreen.route}/$email"){
                                popUpTo(0)
                            }
                        }
                        TypeOfUser.ClientUser -> {
                            navController.navigate(route = "${Screen.ClientUserScreen.route}/$email"){
                                popUpTo(0)
                            }
                        }
                        TypeOfUser.CompanyUser -> {
                            navController.navigate(route = "${Screen.CompanyUserScreen.route}/$email"){
                                popUpTo(0)
                            }
                        }
                        TypeOfUser.ManagerUser -> {
                            navController.navigate(route = "${Screen.ManagerUserScreen.route}/$email"){
                                popUpTo(0)
                            }
                        }
                        TypeOfUser.OperatorUser -> {
                            navController.navigate(route = "${Screen.OperatorUserScreen.route}/$email"){
                                popUpTo(0)
                            }
                        }
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
                    navController.navigate(route = "${Screen.ClientUserScreen.route}/$email"){
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
            route = "${Screen.AdminUserScreen.route}/{userEmail}",
            arguments = listOf(navArgument("userEmail") { type = NavType.StringType })

        ){ AdminUserMainScreen() }

        composable(
            route = "${Screen.ClientUserScreen.route}/{userEmail}",
            arguments = listOf(navArgument("userEmail") { type = NavType.StringType })

        ){ ClientUserMainScreen() }

        composable(
            route = "${Screen.CompanyUserScreen.route}/{userEmail}",
            arguments = listOf(navArgument("userEmail") { type = NavType.StringType })

        ){ CompanyUserMainScreen() }

        composable(
            route = "${Screen.ManagerUserScreen.route}/{userEmail}",
            arguments = listOf(navArgument("userEmail") { type = NavType.StringType })

        ){ ManagerUserMainScreen() }

        composable(
            route = "${Screen.OperatorUserScreen.route}/{userEmail}",
            arguments = listOf(navArgument("userEmail") { type = NavType.StringType })

        ){ OperatorUserMainScreen() }
    }
}