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

@Preview
@Composable
fun SignUpScreen() {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var confirmPassword by remember {
        mutableStateOf("")
    }
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
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    label = stringResource(id = R.string.email),
                    type = TextFieldType.Email
                )

                Spacer(modifier = Modifier.height(30.dp))

                TextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    label = stringResource(id = R.string.password),
                    type = TextFieldType.Password
                )

                Spacer(modifier = Modifier.height(30.dp))

                TextField(
                    value = confirmPassword,
                    onValueChange = {
                        confirmPassword = it
                    },
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
                    onClick = { /*TODO*/ },
                    text = stringResource(id = R.string.sign_up)
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