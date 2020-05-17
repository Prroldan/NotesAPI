package com.salesianostriana.notesapi

import com.salesianostriana.notesapi.users.UserRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.*
import javax.annotation.PostConstruct
import javax.management.relation.Role

interface NotasRepository : JpaRepository<Nota, UUID>{

    @Query("select distinct n from Nota n where n.autor.id = :id")
    fun findNotasLoggeado(id:UUID) : List<Nota>
}

@Component
class InitDataComponent(

    val notasRepository: NotasRepository,
    val userRepository: UserRepository,
    val encodedPassword: BCryptPasswordEncoder
){
    @PostConstruct
    fun initData(){
        val user1 = User("Usuario", encodedPassword.encode("1234"), "Usuario 1","USER")
        userRepository.save(user1)
        val nota1 = Nota("Nota 1", "Esta es la primera nota", LocalDate.now(), LocalDate.now(),user1 )
        val nota2 = Nota("Nota 2", "Esta es la segunda nota", LocalDate.now(), LocalDate.now(),user1 )
        val nota3 = Nota("Nota 3", "Esta es la tercera nota", LocalDate.now(), LocalDate.now(),user1 )
        val nota4 = Nota("Nota 4", "Esta es la cuarta nota", LocalDate.now(), LocalDate.now(),user1 )
        val nota5 = Nota("Nota 5", "Esta es la quinta nota", LocalDate.now(), LocalDate.now(),user1 )
        val nota6 = Nota("Nota 6", "Esta es la sexta nota", LocalDate.now(), LocalDate.now(),user1 )
        notasRepository.save(nota1)
        notasRepository.save(nota2)
        notasRepository.save(nota3)
        notasRepository.save(nota4)
        notasRepository.save(nota5)
        notasRepository.save(nota6)
    }

}

