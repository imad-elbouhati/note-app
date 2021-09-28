package com.imadev.noteapp.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.imadev.noteapp.R
import com.imadev.noteapp.databinding.FragmentNotesBinding
import com.imadev.noteapp.ui.BaseFragment

class NotesFragment : BaseFragment<FragmentNotesBinding>(R.layout.fragment_notes) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_notesFragment_to_addEditNoteFragment2)
        }
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentNotesBinding.inflate(inflater, container, false)
}