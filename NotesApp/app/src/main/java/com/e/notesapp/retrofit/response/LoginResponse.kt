package com.e.notesapp.retrofit.response

import com.e.notesapp.retrofit.UserResponse

data class LoginResponse(
    val token: String,
    val user: UserResponse
)