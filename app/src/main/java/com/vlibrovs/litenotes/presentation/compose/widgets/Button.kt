package com.vlibrovs.litenotes.presentation.compose.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vlibrovs.litenotes.presentation.theme.LiteNotesTheme
import com.vlibrovs.litenotes.util.values.Constants

@Composable
fun Button(
    modifier: Modifier = Modifier
        .width(250.dp)
        .height(50.dp),
    style: ButtonStyle,
    onClick: () -> Unit,
    text: String,
    isLoading: State<Boolean> = mutableStateOf(false)
) {
    LiteNotesTheme {
        val filledButtonColors = MaterialTheme.colorScheme.run {
            ButtonDefaults.buttonColors(
                containerColor = primary, contentColor = onPrimary
            )
        }
        val outlinedButtonColors = MaterialTheme.colorScheme.run {
            ButtonDefaults.outlinedButtonColors(
                contentColor = primary
            )
        }
        when (style) {
            ButtonStyle.Filled -> {
                androidx.compose.material3.Button(
                    modifier = modifier,
                    onClick = onClick,
                    colors = filledButtonColors
                ) {
                    Text(
                        text = text,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
            ButtonStyle.Outlined -> {
                OutlinedButton(
                    modifier = modifier,
                    onClick = onClick, colors = outlinedButtonColors,
                    border = BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.primary)
                ) {
                    AnimatedVisibility(
                        visible = isLoading.value,
                        enter = fadeIn(
                            tween(
                                durationMillis = Constants.AnimationDuration.PROGRESS_BAR,
                                delayMillis = Constants.AnimationDuration.PROGRESS_BAR
                            )
                        ),
                        exit = fadeOut(
                            tween(Constants.AnimationDuration.PROGRESS_BAR)
                        )
                    ) {
                        CircularProgressIndicator()
                    }
                    AnimatedVisibility(
                        visible = !isLoading.value,
                        enter = fadeIn(
                            tween(
                                durationMillis = Constants.AnimationDuration.PROGRESS_BAR,
                                delayMillis = Constants.AnimationDuration.PROGRESS_BAR
                            )
                        ),
                        exit = fadeOut(
                            tween(Constants.AnimationDuration.PROGRESS_BAR)
                        )
                    ) {
                        Text(
                            text = text,
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }
}

sealed class ButtonStyle {
    object Filled : ButtonStyle()
    object Outlined : ButtonStyle()
}