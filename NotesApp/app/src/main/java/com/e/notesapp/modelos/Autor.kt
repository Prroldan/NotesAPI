package com.e.notesapp.modelos

data class Autor(
    val accountNonExpired: Boolean,
    val accountNonLocked: Boolean,
    val authorities: List<Authority>,
    val credentialsNonExpired: Boolean,
    val enabled: Boolean,
    val fullName: String,
    val id: String,
    val password: String,
    val roles: List<String>,
    val username: String
)