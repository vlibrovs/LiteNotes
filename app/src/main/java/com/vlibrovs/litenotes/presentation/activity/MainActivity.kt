package com.vlibrovs.litenotes.presentation.activity

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.vlibrovs.litenotes.presentation.compose.screen.*
import com.vlibrovs.litenotes.presentation.theme.LiteNotesTheme
import com.vlibrovs.litenotes.presentation.viewmodel.EditNoteScreenViewModel
import com.vlibrovs.litenotes.presentation.viewmodel.MainScreenViewModel
import com.vlibrovs.litenotes.presentation.viewmodel.SignInScreenViewModel
import com.vlibrovs.litenotes.presentation.viewmodel.SignUpScreenViewModel
import com.vlibrovs.litenotes.util.extensions.getNote
import com.vlibrovs.litenotes.util.screen.Screen
import com.vlibrovs.litenotes.util.values.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val signInScreenViewModel by viewModel<SignInScreenViewModel>()
    private val signUpScreenViewModel by viewModel<SignUpScreenViewModel>()
    private val mainScreenViewModel by viewModel<MainScreenViewModel>()
    private val editNoteScreenViewModel by viewModel<EditNoteScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            var signedIn by remember { mutableStateOf(true) }
            LiteNotesTheme {
                NavHost(
                    navController = navController,
                    startDestination = if (signedIn) Screen.MainScreen.route else Screen.StartScreen.route
                ) {
                    composable(route = Screen.StartScreen.route) {
                        StartScreen(navController)
                    }
                    composable(route = Screen.SigInScreen.route) {
                        SignInScreen(
                            viewModel = signInScreenViewModel,
                            navController = navController
                        )
                    }
                    composable(route = Screen.SignUpScreen.route) {
                        SignUpScreen(
                            viewModel = signUpScreenViewModel,
                            navController = navController
                        )
                    }
                    composable(route = Screen.MainScreen.route) {
                        MainScreen(
                            viewModel = mainScreenViewModel,
                            navController = navController
                        )
                    }
                    composable(route = Screen.EditNoteScreen.route) {
                        EditNoteScreen(
                            viewModel = editNoteScreenViewModel,
                            navController = navController
                        )
                    }
                    composable(
                        route = Screen.NoteViewScreen.route + "/{${Constants.NavArguments.NOTE_TITLE}}/{${Constants.NavArguments.NOTE_CONTENT}}",
                        arguments = listOf(
                            navArgument(name = Constants.NavArguments.NOTE_TITLE) {
                                type = NavType.StringType
                                nullable = false
                            },
                            navArgument(name = Constants.NavArguments.NOTE_CONTENT) {
                                type = NavType.StringType
                                nullable = false
                            },
                        )
                    ) { entry ->
                        NoteViewScreen(
                            navController = navController,
                            note = entry.getNote(),
                            viewModel = editNoteScreenViewModel
                        )
                    }
                }
            }
        }
    }
}