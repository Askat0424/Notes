package com.example.notes.presentation.ui.fragments.create_edit_note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.domain.model.Note
import com.example.notes.domain.usecase.CreateNoteUseCase
import com.example.notes.domain.usecase.UpdateNoteUseCase
import com.example.notes.domain.utils.Resource
import com.example.notes.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateEditNoteViewModel @Inject constructor(
    private val createNoteUseCase: CreateNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
) : ViewModel() {

    private val _createNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val createNoteState = _createNoteState.asStateFlow()

    private val _updateNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val updateNoteState = _updateNoteState.asStateFlow()

    fun createNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            createNoteUseCase.createNote(note).collect { res ->
                when (res) {
                    is Resource.Error -> {
                        _createNoteState.value = UIState.Error(res.message!!)
                    }

                    is Resource.Loading -> {
                        _createNoteState.value = UIState.Loading()
                    }

                    is Resource.Success -> {
                        if (res.data != null) {
                            _createNoteState.value = UIState.Success(res.data)
                        }
                    }
                }
            }
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            updateNoteUseCase.updateNote(note).collect { res ->
                when (res) {
                    is Resource.Error -> {
                        _updateNoteState.value = UIState.Error(res.message!!)
                    }

                    is Resource.Loading -> {
                        _updateNoteState.value = UIState.Loading()
                    }

                    is Resource.Success -> {
                        if (res.data != null) {
                            _updateNoteState.value = UIState.Success(res.data)
                        }
                    }
                }
            }
        }
    }
}