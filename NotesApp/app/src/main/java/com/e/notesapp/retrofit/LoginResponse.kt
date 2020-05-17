package com.e.notesapp.retrofit

data class LoginResponse(
    val token: String,
    val user: UserResponse
)