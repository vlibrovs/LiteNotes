package com.vlibrovs.litenotes.presentation.compose.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vlibrovs.litenotes.R
import com.vlibrovs.litenotes.presentation.compose.widgets.Button
import com.vlibrovs.litenotes.presentation.compose.widgets.ButtonStyle
import com.vlibrovs.litenotes.presentation.compose.widgets.TextField
import com.vlibrovs.litenotes.presentation.compose.widgets.TextFieldType
import com.vlibrovs.litenotes.presentation.theme.LiteNotesTheme
import com.vlibrovs.litenotes.presentation.viewmodel.SignInScreenViewModel

@Composable
fun SignInScreen(
    viewModel: SignInScreenViewModel
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
                text = stringResource(id = R.string.sign_in),
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
                TextButton(modifier = Modifier.align(End), onClick = { /*TODO*/ }) {
                    Text(
                        text = stringResource(id = R.string.forgot_password),
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        textAlign = TextAlign.End
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    style = ButtonStyle.Filled,
                    onClick = { /*TODO*/ },
                    text = stringResource(id = R.string.sign_in)
                )

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    style = ButtonStyle.Outlined,
                    onClick = { /*TODO*/ },
                    text = stringResource(id = R.string.back)
                )
            }
        }
    }
}