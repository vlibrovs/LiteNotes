package com.vlibrovs.litenotes.presentation.compose.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.vlibrovs.litenotes.domain.model.note.Note
import com.vlibrovs.litenotes.presentation.theme.LiteNotesTheme
import com.vlibrovs.litenotes.presentation.theme.primaryContainerOnClick
import com.vlibrovs.litenotes.presentation.theme.secondaryContainerOnClick
import com.vlibrovs.litenotes.presentation.theme.tertiaryContainerOnClick

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteItem(
    modifier: Modifier = Modifier.width(160.dp),
    maxContentHeight: Dp = 200.dp,
    innerPadding: PaddingValues = PaddingValues(
        top = 15.dp, start = 15.dp, end = 15.dp
    ),
    outerPadding: PaddingValues = PaddingValues(),
    style: NoteItemStyle = NoteItemStyle.Primary,
    state: NoteItemState = NoteItemState.Default,
    note: Note,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val backgroundColor by animateColorAsState(
        targetValue = when (style) {
            NoteItemStyle.Primary -> {
                if (isPressed) MaterialTheme.colorScheme.primaryContainerOnClick
                else MaterialTheme.colorScheme.primaryContainer
            }
            NoteItemStyle.Secondary -> {
                if (isPressed) MaterialTheme.colorScheme.secondaryContainerOnClick
                else MaterialTheme.colorScheme.secondaryContainer
            }
            NoteItemStyle.Tertiary -> {
                if (isPressed) MaterialTheme.colorScheme.tertiaryContainerOnClick
                else MaterialTheme.colorScheme.tertiaryContainer
            }
        },
        animationSpec = tween(20)
    )
    LiteNotesTheme {
        Column(
            modifier = modifier
                .padding(outerPadding)
                .background(
                    shape = RoundedCornerShape(10.dp), color = backgroundColor
                )
                .border(
                    width = 2.dp,
                    color = if (state is NoteItemState.Selectable && state.isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(innerPadding)
                .combinedClickable(interactionSource = interactionSource,
                    indication = null,
                    onClick = { onClick() },
                    onLongClick = { onLongClick() })

        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = note.title,
                style = MaterialTheme.typography.titleSmall,
                color = when (style) {
                    NoteItemStyle.Primary -> MaterialTheme.colorScheme.primary
                    NoteItemStyle.Secondary -> MaterialTheme.colorScheme.secondary
                    NoteItemStyle.Tertiary -> MaterialTheme.colorScheme.tertiary
                },
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier.heightIn(0.dp, maxContentHeight),
                text = note.content,
                style = MaterialTheme.typography.bodySmall,
                color = when (style) {
                    NoteItemStyle.Primary -> MaterialTheme.colorScheme.onPrimaryContainer
                    NoteItemStyle.Secondary -> MaterialTheme.colorScheme.onSecondaryContainer
                    NoteItemStyle.Tertiary -> MaterialTheme.colorScheme.onTertiaryContainer
                },
                overflow = TextOverflow.Ellipsis,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(31.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                AnimatedVisibility(
                    visible = state is NoteItemState.Selectable,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    RadioButton(isSelected = state is NoteItemState.Selectable && state.isSelected)
                }
            }
        }
    }
}

sealed class NoteItemStyle {
    object Primary : NoteItemStyle()
    object Secondary : NoteItemStyle()
    object Tertiary : NoteItemStyle()
}

sealed class NoteItemState {
    object Default : NoteItemState()
    data class Selectable(val isSelected: Boolean = false) : NoteItemState()
}