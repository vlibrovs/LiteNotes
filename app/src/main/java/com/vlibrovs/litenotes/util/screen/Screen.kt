package com.vlibrovs.litenotes.util.screen

import com.vlibrovs.litenotes.domain.model.note.Note

sealed class Screen(val route: String) {
    object StartScreen : Screen("start_screen")
    object SigInScreen : Screen("sign_in_screen")
    object SignUpScreen : Screen("sign_up_screen")
    object MainScreen : Screen("main_screen")
    object EditNoteScreen : Screen("edit_note_screen")
    object NoteViewScreen : Screen("note_view_screen") {
        fun routeWith(note: Note): String {
            return NoteViewScreen.route + "/${note.title}/${note.content}"
        }
    }
}
