package com.example.notes.data.local

import androidx.room.*
import com.example.notes.data.model.NoteEntity


@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): List<NoteEntity>

    @Insert
    suspend fun createNotes(noteEntity: NoteEntity)

    @Update
    suspend fun updateNotes(noteEntity: NoteEntity)

    @Delete
    suspend fun deleteNotes(noteEntity: NoteEntity)
}