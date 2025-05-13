package com.example.todoapp.Data.model.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todoapp.Data.model.presentation.components.SignUpPage
import com.example.todoapp.Data.model.presentation.screen.dashboard.DashboardScreen
import com.example.todoapp.Data.model.presentation.components.LoginScreen

@Composable
fun TodoNavGraph(navController:NavHostController){
    NavHost(navController=navController, startDestination = "signup"){
        composable("signup"){
            SignUpPage(navController)
        }
        composable("login"){
            LoginScreen(navController)
        }
     composable ("dashboard"){
         DashboardScreen(
navController
         )
     }
    }
}