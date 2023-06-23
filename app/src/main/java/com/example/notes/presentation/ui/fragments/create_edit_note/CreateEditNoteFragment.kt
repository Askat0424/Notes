package com.example.notes.presentation.ui.fragments.create_edit_note

import android.provider.Contacts.SettingsColumns.KEY
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.notes.R
import com.example.notes.databinding.FragmentCreateEditNoteBinding
import com.example.notes.domain.model.Note
import com.example.notes.presentation.base.BaseFragment
import com.example.notes.presentation.utils.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateEditNoteFragment : BaseFragment(R.layout.fragment_create_edit_note) {

    private val binding by viewBinding(FragmentCreateEditNoteBinding::bind)
    private val viewModel by viewModels<CreateEditNoteViewModel>()
    private var note: Note? = null

    override fun initClickListeners() {
        note = arguments?.getSerializable(KEY) as Note?
        binding.etTitle.setText(note?.title)
        binding.etDescription.setText(note?.description)
        if (note == null) {
            binding.btnSave.setOnClickListener {
                viewModel.createNote(
                    Note(
                        title = binding.etTitle.text.toString(),
                        description = binding.etDescription.text.toString()
                    )
                )
            }
        } else {

            binding.btnSave.text = "Update"
            binding.btnSave.setOnClickListener {
                note!!.title = binding.etTitle.text.toString()
                note!!.description = binding.etDescription.text.toString()
                viewModel.updateNote(
                    note!!
                )
            }

        }
    }


        override fun setupObservers() {
            viewModel.createNoteState.collectUIState(
                state = {
                    binding.progressBar.isVisible = it is UIState.Loading
                },
                onSuccess = {
                    findNavController().navigateUp()
                }
            )

            viewModel.updateNoteState.collectUIState(
                state = {
                    binding.progressBar.isVisible = it is UIState.Loading
                },
                onSuccess = {
                    findNavController().navigateUp()
                }
            )
        }
    }