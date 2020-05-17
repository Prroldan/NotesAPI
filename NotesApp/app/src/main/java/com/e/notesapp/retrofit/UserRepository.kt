package com.e.notesapp.retrofit

import androidx.lifecycle.LiveData
import javax.inject.Inject

class UserRepository @Inject constructor(var userService: UserService) {

    var notesAppClient:NotesApiClient = NotesApiClient()

    fun doLogin(userRequest: UserRequest){
        userService.login()
    }
}