package com.ayi.curso.rest.serv.app.servicies.impl;

import com.ayi.curso.rest.serv.app.dtos.response.persons.PersonResponseDTO;
import com.ayi.curso.rest.serv.app.entities.PersonEntity;
import com.ayi.curso.rest.serv.app.repositories.IPersonRepository;
import com.ayi.curso.rest.serv.app.servicies.IPersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service //Para que pueda ser inyectado desde otro lugar
@Slf4j
@Transactional //Hace el trabajo de JPA (commit - begin - rollback - etc)
public class PersonServiceImpl extends Exception implements IPersonService {

    @Autowired
    private IPersonRepository personRepository;


    @Override
    public List<PersonResponseDTO> findAllPersons() {

        List<PersonResponseDTO> personResponseDTOs;

        List<PersonEntity> personEntities = personRepository.findAll();

        personResponseDTOs = personEntities.stream()
                .map(lt -> new PersonResponseDTO(
                        lt.getIdPerson(),
                        lt.getFirstName(),
                        lt.getLastName(),
                        lt.getTypeDocument(),
                        lt.getNumberDocument(),
                        lt.getDateCreated(),
                        lt.getDateModified()
                ))
                .collect(Collectors.toList());

        return personResponseDTOs;
    }
}
