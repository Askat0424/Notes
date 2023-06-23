package com.example.notes.presentation.ui.fragments.create_edit_note

import com.example.notes.domain.model.Note
import com.example.notes.domain.usecase.CreateNoteUseCase
import com.example.notes.domain.usecase.UpdateNoteUseCase
import com.example.notes.presentation.base.BaseViewModel
import com.example.notes.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CreateEditNoteViewModel @Inject constructor(
    private val createNoteUseCase: CreateNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
) : BaseViewModel() {

    private val _createNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val createNoteState = _createNoteState.asStateFlow()

    private val _updateNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val updateNoteState = _updateNoteState.asStateFlow()

    fun createNote(note: Note) {
        createNoteUseCase.createNote(note).collectData(_createNoteState)
    }

    fun updateNote(note: Note) {
        updateNoteUseCase.updateNote(note).collectData(_updateNoteState)
    }
}