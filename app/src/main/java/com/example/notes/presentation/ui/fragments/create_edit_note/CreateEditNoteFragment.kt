package com.example.notes.presentation.ui.fragments.create_edit_note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.notes.R
import com.example.notes.databinding.FragmentCreateEditNoteBinding
import com.example.notes.domain.model.Note
import com.example.notes.presentation.utils.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreateEditNoteFragment : Fragment(R.layout.fragment_create_edit_note) {

    private val binding by viewBinding(FragmentCreateEditNoteBinding::bind)
    private val viewModel by viewModels<CreateEditNoteViewModel>()
    private var note: Note? = null
    private var noteIsNull = true
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            viewModel.createNote(
                Note(
                    title = binding.etTitle.text.toString(),
                    description = binding.etDescription.text.toString()
                )
            )
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.createNoteState.collect { state ->
                    when (state) {
                        is UIState.Empty -> {}
                        is UIState.Error -> {
                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT)
                                .show()
                        }

                        is UIState.Loading -> {
                            binding.progressBar.isVisible = true
                        }

                        is UIState.Success -> {
                            findNavController().navigateUp()
                        }
                    }
                }
            }
        }
    }
}