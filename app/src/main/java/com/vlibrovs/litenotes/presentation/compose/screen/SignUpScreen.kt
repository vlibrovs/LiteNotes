package com.vlibrovs.litenotes.presentation.compose.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vlibrovs.litenotes.R
import com.vlibrovs.litenotes.presentation.compose.widgets.Button
import com.vlibrovs.litenotes.presentation.compose.widgets.ButtonStyle
import com.vlibrovs.litenotes.presentation.compose.widgets.TextField
import com.vlibrovs.litenotes.presentation.compose.widgets.TextFieldType
import com.vlibrovs.litenotes.presentation.theme.LiteNotesTheme
import com.vlibrovs.litenotes.presentation.viewmodel.SignUpScreenViewModel
import com.vlibrovs.litenotes.util.screen.Screen

@Composable
fun SignUpScreen(
    viewModel: SignUpScreenViewModel,
    navController: NavController
) {
    LiteNotesTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.sign_up),
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = viewModel.emailState,
                    onValueChange = viewModel.onEmailValueChange,
                    label = stringResource(id = R.string.email),
                    type = TextFieldType.Email
                )

                Spacer(modifier = Modifier.height(30.dp))

                TextField(
                    value = viewModel.passwordState,
                    onValueChange = viewModel.onPasswordValueChange,
                    label = stringResource(id = R.string.password),
                    type = TextFieldType.Password
                )

                Spacer(modifier = Modifier.height(30.dp))

                TextField(
                    value = viewModel.confirmPasswordState,
                    onValueChange = viewModel.onConfirmPasswordValueChange,
                    label = stringResource(id = R.string.confirm_password),
                    type = TextFieldType.Password
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    style = ButtonStyle.Filled,
                    onClick = {
                        viewModel.signUp {
                            navController.navigate(Screen.MainScreen.route)
                        }
                    },
                    text = stringResource(id = R.string.sign_up),
                    isLoading = viewModel.isLoading
                )

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    style = ButtonStyle.Outlined,
                    onClick = { navController.popBackStack() },
                    text = stringResource(id = R.string.back)
                )
            }
        }
    }
}