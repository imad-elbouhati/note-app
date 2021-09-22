package com.imadev.noteapp.data.remote.requests

data class AddOwnerRequest(
    val owner: String,
    val noteID: String
)