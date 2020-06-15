package com.salesianostriana.notesapi

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate
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
    fun nuevaNota(@RequestBody newNota: NewNotaDto, @AuthenticationPrincipal user:User) = ResponseEntity.status(HttpStatus.CREATED)
            .body(notasRepository.save(newNota.toNota(user)).toNotaDto())

    @DeleteMapping("/{id}")
    fun borrarNota(@PathVariable id: UUID) {
        var result: Nota
        result = getNotaById(id)
        notasRepository.delete(result)


    }

    @PutMapping("/{id}")
    fun editNota(@PathVariable id: UUID, @RequestBody edit: NewNotaDto): NotaDto {
        var result:Nota
        result = getNotaById(id)
        result.titulo = edit.titulo
        result.contenido = edit.contenido
        result.fechaUltEdicion = LocalDate.now()
        ResponseEntity.status(HttpStatus.CREATED)
                .body(notasRepository.save(result))


        return result.toNotaDto()
    }



}