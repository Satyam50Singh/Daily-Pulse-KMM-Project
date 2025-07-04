package com.example.dailypulse.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dailypulse.android.screens.AboutScreen
import com.example.dailypulse.android.screens.ArticleScreen
import com.example.dailypulse.android.screens.Screens
import com.example.dailypulse.android.screens.SourcesScreen
import com.example.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppScaffold() {

    val navController = rememberNavController()

    Scaffold {
        AppNavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        )
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screens.ARTICLES.route,
        modifier = modifier
    ) {
        composable(Screens.ARTICLES.route) {
            ArticleScreen(
                onAboutButtonClick = { navController.navigate(Screens.ABOUT_DEVICE.route) },
                onSourcesButtonClick = { navController.navigate(Screens.SOURCES.route) }
            )
        }

        composable(Screens.ABOUT_DEVICE.route) {
            AboutScreen(onUpButtonClick = { navController.popBackStack() })
        }

        composable(Screens.SOURCES.route) {
            SourcesScreen(onBackClick = { navController.popBackStack() })
        }
    }
}


