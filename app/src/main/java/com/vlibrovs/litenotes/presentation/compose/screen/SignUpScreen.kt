package com.vlibrovs.litenotes.presentation.compose.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vlibrovs.litenotes.R
import com.vlibrovs.litenotes.presentation.compose.widgets.Button
import com.vlibrovs.litenotes.presentation.compose.widgets.ButtonStyle
import com.vlibrovs.litenotes.presentation.compose.widgets.TextField
import com.vlibrovs.litenotes.presentation.compose.widgets.TextFieldType
import com.vlibrovs.litenotes.presentation.theme.LiteNotesTheme
import com.vlibrovs.litenotes.presentation.theme.fonts.Poppins
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
                Column {
                    AnimatedVisibility(
                        visible = viewModel.emailErrorState.value,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Text(
                            text = stringResource(
                                id = viewModel.emailErrorStringResourceState.value
                                    ?: R.string.unknown_error
                            ),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.error,
                                fontSize = 14.sp,
                                fontFamily = FontFamily.Poppins
                            )
                        )
                    }
                    TextField(
                        value = viewModel.emailState,
                        onValueChange = viewModel.onEmailValueChange,
                        label = stringResource(id = R.string.email),
                        type = TextFieldType.Email,
                        isError = viewModel.emailErrorState.value
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                Column {
                    AnimatedVisibility(
                        visible = viewModel.passwordErrorState.value,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Text(
                            text = stringResource(
                                id = viewModel.passwordErrorStringResourceState.value
                                    ?: R.string.unknown_error
                            ),
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.error,
                                fontSize = 14.sp,
                                fontFamily = FontFamily.Poppins
                            )
                        )
                    }
                    TextField(
                        value = viewModel.passwordState,
                        onValueChange = viewModel.onPasswordValueChange,
                        label = stringResource(id = R.string.password),
                        type = TextFieldType.Password,
                        isError = viewModel.passwordErrorState.value
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                Column {
                    AnimatedVisibility(
                        visible = viewModel.confirmPasswordErrorState.value,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Row {
                            Text(
                                text = stringResource(
                                    id = viewModel.confirmPasswordStringResourceState.value
                                        ?: R.string.unknown_error
                                ),
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.error,
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily.Poppins
                                )
                            )
                            IconButton(onClick = {}) {
                                
                            }
                        }
                    }
                    TextField(
                        value = viewModel.confirmPasswordState,
                        onValueChange = viewModel.onConfirmPasswordValueChange,
                        label = stringResource(id = R.string.confirm_password),
                        type = TextFieldType.Password,
                        isError = viewModel.confirmPasswordErrorState.value
                    )
                }
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