package com.imadev.noteapp.ui.addeditnotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.imadev.noteapp.R
import com.imadev.noteapp.databinding.FragmentAddEditNoteBinding
import com.imadev.noteapp.ui.BaseFragment

class AddEditNoteFragment : BaseFragment<FragmentAddEditNoteBinding>(R.layout.fragment_add_edit_note) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAddEditNoteBinding.inflate(inflater, container, false)
}