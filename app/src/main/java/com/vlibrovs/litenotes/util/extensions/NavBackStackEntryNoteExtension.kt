package com.vlibrovs.litenotes.util.extensions

import androidx.navigation.NavBackStackEntry
import com.vlibrovs.litenotes.domain.model.note.Note

fun NavBackStackEntry.getNote(): Note {
    return try {
        Note(
            title = arguments!!.getString("title")!!,
            content = arguments!!.getString("content")!!,
        )
    } catch (e: Exception) {
        Note("", "")
    }
}