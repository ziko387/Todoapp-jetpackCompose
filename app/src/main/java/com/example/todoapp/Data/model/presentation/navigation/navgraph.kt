package com.example.todoapp.Data.model.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todoapp.Data.model.presentation.screen.dashboard.DashboardScreen

@Composable
fun TodoNavGraph(navController:NavHostController){
    NavHost(navController=navController, startDestination = "Dashboard"){
     composable ("dashboard"){
         DashboardScreen(

         )
     }
    }
}