package com.e.notesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.e.notesapp.retrofit.LoginResponse
import com.e.notesapp.retrofit.UserRepository
import com.e.notesapp.retrofit.UserRequest
import javax.inject.Inject

class UserViewModel @Inject constructor(
    userRepository: UserRepository
): ViewModel() {
    var userRepository = userRepository
    lateinit var logResponse: LiveData<LoginResponse>

    fun doLogin(userRequest: UserRequest){
        userRepository.doLogin(userRequest)
    }
}