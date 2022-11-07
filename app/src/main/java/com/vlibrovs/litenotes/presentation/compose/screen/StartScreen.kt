package com.vlibrovs.litenotes.presentation.compose.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vlibrovs.litenotes.R
import com.vlibrovs.litenotes.presentation.compose.widgets.Button
import com.vlibrovs.litenotes.presentation.compose.widgets.ButtonStyle
import com.vlibrovs.litenotes.presentation.theme.LiteNotesTheme
import com.vlibrovs.litenotes.util.screen.Screen

@Composable
fun StartScreen(
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
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = stringResource(id = R.string.start_screen_label),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    style = ButtonStyle.Filled,
                    onClick = { navController.navigate(Screen.SigInScreen.route) },
                    text = stringResource(id = R.string.sign_in)
                )

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    style = ButtonStyle.Outlined,
                    onClick = { navController.navigate(Screen.SignUpScreen.route) },
                    text = stringResource(id = R.string.sign_up)
                )
            }
        }
    }
}