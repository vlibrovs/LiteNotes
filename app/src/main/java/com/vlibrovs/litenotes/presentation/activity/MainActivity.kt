package com.vlibrovs.litenotes.presentation.activity

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.vlibrovs.litenotes.presentation.compose.screen.MainScreen
import com.vlibrovs.litenotes.presentation.compose.screen.SignInScreen
import com.vlibrovs.litenotes.presentation.compose.screen.SignUpScreen
import com.vlibrovs.litenotes.presentation.theme.LiteNotesTheme
import com.vlibrovs.litenotes.presentation.viewmodel.MainScreenViewModel
import com.vlibrovs.litenotes.presentation.viewmodel.SignInScreenViewModel
import com.vlibrovs.litenotes.presentation.viewmodel.SignUpScreenViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val signInScreenViewModel by viewModel<SignInScreenViewModel>()
    private val signUpScreenViewModel by viewModel<SignUpScreenViewModel>()
    private val mainScreenViewModel by viewModel<MainScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LiteNotesTheme {
                MainScreen(viewModel = mainScreenViewModel)
            }
        }
    }
}