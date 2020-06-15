package com.e.notesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.e.notesapp.dto.LoginDto
import com.e.notesapp.retrofit.response.LoginResponse
import com.e.notesapp.retrofit.UserRepository


class UserViewModel: ViewModel() {
    private var userRepository = UserRepository()
    private lateinit var response: LiveData<LoginResponse>

    init {

    }

    fun doLogin(loginDto: LoginDto): LiveData<LoginResponse> {
        response = userRepository.login(loginDto)
        return response
    }



}