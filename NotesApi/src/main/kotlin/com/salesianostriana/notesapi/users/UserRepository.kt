package com.salesianostriana.notesapi.users

import com.salesianostriana.notesapi.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, UUID> {

    fun findByUsername(username : String) : Optional<User>

}