package com.example.notes.presentation.ui.fragments.list_note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.domain.model.Note
import com.example.notes.domain.usecase.DeleteNoteUseCase
import com.example.notes.domain.usecase.GetAllNotesUseCase
import com.example.notes.domain.utils.Resource
import com.example.notes.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListNoteViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    private val _getAllNotesState = MutableStateFlow<UIState<List<Note>>>(UIState.Empty())
    val getAllNotesState: StateFlow<UIState<List<Note>>> = _getAllNotesState

    private val _deleteNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val deleteNoteState: MutableStateFlow<UIState<Unit>> = _deleteNoteState

    fun getAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllNotesUseCase.getAllNotes().collect { res ->
                when (res) {
                    is Resource.Error -> {
                        _getAllNotesState.value = UIState.Error(res.message!!)
                    }

                    is Resource.Loading -> {
                        _getAllNotesState.value = UIState.Loading()
                    }

                    is Resource.Success -> {
                        if (res.data != null) {
                            _getAllNotesState.value = UIState.Success(res.data)
                        }
                    }
                }
            }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteNoteUseCase.deleteNote(note).collect { res ->
                when (res) {
                    is Resource.Error -> {
                        _deleteNoteState.value = UIState.Error(res.message!!)
                    }

                    is Resource.Loading -> {
                        _deleteNoteState.value = UIState.Loading()
                    }

                    is Resource.Success -> {
                        _deleteNoteState.value = UIState.Success(res.data!!)
                    }
                }
            }
        }
    }
}