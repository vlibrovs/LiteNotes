package com.vlibrovs.litenotes.domain.model.note

data class Note(
    val title: String,
    val content: String
) {
    val size get() = content.length / 50
}
