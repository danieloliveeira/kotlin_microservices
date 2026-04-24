package com.daniel.microservices.services

import com.daniel.microservices.exceptions.ResourceNotFoundException
import com.daniel.microservices.model.Person
import com.daniel.microservices.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService(
    @Autowired
    private var repository: PersonRepository
) {

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findById(id: Long): Person {
        logger.info("Finding one person!")
        return repository.findById(id)
            .orElseThrow{ResourceNotFoundException("No records founds for this ID!")}
    }

    fun create(person: Person): Person{
        logger.info("Creating one person with name ${person.firstName}!")
        return repository.save(person)
    }

    fun update(person: Person): Person {
        logger.info("Updating one person with name ${person.id}!")
        val entity =  repository.findById(person.id)
            .orElseThrow{ResourceNotFoundException("No records founds for this ID!")}

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.adress = person.adress
        entity.gender = person.gender

        return repository.save(person)
    }

    fun delete(id: Long){
        logger.info("Deleting one person with ID $id!")
        val person =  repository.findById(id)
            .orElseThrow{ResourceNotFoundException("No records founds for this ID!")}
        repository.delete(person)
    }

    fun findAll(): List<Person> {
        logger.info("Finding all people!")
        return repository.findAll()
    }

}