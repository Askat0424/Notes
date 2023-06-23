package com.example.notes.domain.model

import java.io.Serializable

data class Note(
    val id: Int = DEFAULT_ID,
    var title: String,
    var description: String
):Serializable {
    companion object {
        const val DEFAULT_ID = 0
    }
}