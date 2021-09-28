package com.imadev.noteapp.ui.auth

import androidx.lifecycle.ViewModel
import com.imadev.noteapp.data.remote.requests.AccountRequest
import com.imadev.noteapp.repositories.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {
    fun register(email: String, password: String) = repository.register(AccountRequest(email, password))

}