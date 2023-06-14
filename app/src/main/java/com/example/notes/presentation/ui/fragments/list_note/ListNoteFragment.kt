package com.example.notes.presentation.ui.fragments.list_note

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
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.notes.R
import com.example.notes.databinding.FragmentListNoteBinding
import com.example.notes.domain.model.Note
import com.example.notes.presentation.ui.fragments.list_note.adapter.ListNoteAdapter
import com.example.notes.presentation.utils.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListNoteFragment : Fragment(R.layout.fragment_list_note) {

    private val binding by viewBinding(FragmentListNoteBinding::bind)
    private val adapter by lazy { ListNoteAdapter(this::deleteNote, this::updateNote) }
    private val viewModel by viewModels<ListNoteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllNotes()

        binding.rvNotes.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.rvNotes.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.getAllNotesState.collect { state ->
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
                            adapter.addList(state.data)
                        }
                    }
                }
            }
        }

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listNoteFragment_to_createNoteFragment)
        }
    }

    private fun deleteNote(note: Note) {
        viewModel.deleteNote(note)
        adapter.delete(note)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.deleteNoteState.collect { state ->
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
                        }
                    }
                }
            }
        }
    }

    private fun updateNote(note: Note) {
        val bundle =Bundle()
        bundle.putSerializable("note", note)
        findNavController().navigate(R.id.createNoteFragment, bundle)
    }
}