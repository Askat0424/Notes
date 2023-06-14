package com.example.notes.domain.model

import java.io.Serializable

data class Note(
    val id: Int = DEFAULT_ID,
    val title: String ,
    val description: String
):Serializable {
    companion object {
        const val DEFAULT_ID = 0
    }
}