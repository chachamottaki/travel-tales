package com.example.traveltales

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.traveltales.viewmodel.LoginViewModel

@Composable
fun TravelTalesApp(
    viewModel: LoginViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = TravelTalesScreen.valueOf(
        backStackEntry?.destination?.route ?: TravelTalesScreen.Login.name
    )

    Scaffold(
        topBar = {
            TravelTalesAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val loginResult by viewModel.loginResult.collectAsState()

        NavHost(
            navController = navController,
            startDestination = TravelTalesScreen.Login.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = TravelTalesScreen.Login.name) {
                LoginPage(
                    viewModel = viewModel,
                    onLoginClick = { username, password ->
                        viewModel.login(username, password)
                    }
                )
            }
            composable(route = TravelTalesScreen.Home.name) {
                HomePage(viewModel = viewModel)
            }
        }

        LaunchedEffect(loginResult) {
            loginResult?.let {
                if (it.isSuccess) {
                    navController.navigate(TravelTalesScreen.Home.name)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelTalesAppBar(
    currentScreen: TravelTalesScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                //if i want to add text to my topbar
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}
