package com.salesianostriana.notesapi.users

import com.salesianostriana.notesapi.NotasRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("notas/")
class NotasController(
        val notasRepository: NotasRepository,
        val userRepository: UserRepository
){


}