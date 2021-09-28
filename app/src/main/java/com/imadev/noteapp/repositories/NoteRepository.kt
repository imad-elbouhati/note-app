package com.imadev.noteapp.repositories

import com.imadev.noteapp.data.remote.NoteApi
import com.imadev.noteapp.data.remote.requests.AccountRequest
import com.imadev.noteapp.other.networkBoundResource
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteApi: NoteApi
) {
    fun register(accountRequest:AccountRequest) = networkBoundResource {
        noteApi.register(accountRequest)
    }
}