package com.salesianostriana.notesapi

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
@RequestMapping("/notas")
class NotaController (val notasRepository: NotasRepository) {

    private fun getAllNotasUser(id: UUID): List<Nota> {
        var result: List<Nota>
        with(notasRepository) {
            result = findNotasLoggeado(id)
        }
        if (result.isEmpty())
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "No hay notas creadas por este usuario")
        return result
    }

    private fun getNotaById(id:UUID): Nota {
        var result: Optional<Nota>
        with(notasRepository) {
            result = findById(id)
        }
        return result.orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "No existe la nota con el id:  ${id}")}


    }

    @GetMapping("/")
    private fun getallNotas(): List<Nota> {
        var result: List<Nota>
        with(notasRepository){
            result = findAll()
        }

        if (result.isEmpty())
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha creado ninguna nota ")

        return result
    }

    @GetMapping("/user/{id}")
    fun getNotasByUser(@PathVariable id:UUID) = getAllNotasUser(id).map { it.toNotaDto() }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID) =  getNotaById(id).toNotaDto()

    @PostMapping("/")
    fun nuevaSerie(@RequestBody newNota: NotaDto) = ResponseEntity.status(HttpStatus.CREATED)
            .body(notasRepository.save(newNota.toNota()).toNotaDto())

}