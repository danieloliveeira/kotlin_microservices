package com.daniel.microservices.controller

import com.daniel.microservices.model.Person
import com.daniel.microservices.services.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/persons"], produces = [MediaType.APPLICATION_JSON_VALUE])
class PersonController(
    @Autowired
    private val service: PersonService,
) {

    @GetMapping(value = ["/{id}"])
    fun person(
        @PathVariable(value = "id") id: Long,
    ) : Person {
        return service.findById(id)
    }

    @PostMapping()
    fun personCreate(
        @RequestBody person: Person
    ) : Person {
        return service.create(person)
    }

    @PutMapping()
    fun updateCreate(
        @RequestBody person: Person
    ) : Person {
        return service.update(person)
    }

    @DeleteMapping(value = ["/{id}"])
    fun personDelete(
        @PathVariable id: Long
    ): ResponseEntity<*> {
        service.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }

    @GetMapping()
    fun personAll() : List<Person>{
        return service.findAll()
    }

}