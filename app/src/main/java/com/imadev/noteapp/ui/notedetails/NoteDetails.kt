package com.imadev.noteapp.ui.notedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.imadev.noteapp.R
import com.imadev.noteapp.databinding.FragmentNoteDetailBinding
import com.imadev.noteapp.ui.BaseFragment

class NoteDetails : BaseFragment<FragmentNoteDetailBinding>(R.layout.fragment_note_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentNoteDetailBinding.inflate(inflater, container, false)
}