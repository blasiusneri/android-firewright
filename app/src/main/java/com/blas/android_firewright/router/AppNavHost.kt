package com.blas.android_firewright.router

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.blas.android_firewright.main.ui_layer.DetailScreen
import com.blas.android_firewright.main.ui_layer.HomeScreen
import com.blas.android_firewright.main.ui_layer.MainViewModel
import com.blas.android_firewright.main.ui_layer.model.Pokemon

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: MainViewModel
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Home.route
    ) {
       composable(route = Screen.Home.route) {
           HomeScreen(
               viewModel = viewModel,
               onItemClick = { pokemon ->
                   navController.navigate(Screen.Details.createRoute(pokemon.entryNumber))
               }
           )
       }
       composable (
           route = "detail/{id}",
           arguments = listOf(
               navArgument("id") {
                   type = NavType.IntType
               }
           )
       ) {
           backStackEntry ->
           val id = backStackEntry.arguments?.getInt("id")
           DetailScreen(id.toString())
       }
    }
}