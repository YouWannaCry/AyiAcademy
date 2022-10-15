package com.ayi.curso.rest.serv.app.servicies;

import com.ayi.curso.rest.serv.app.dtos.response.persons.PersonResponseDTO;

import java.util.List;

public interface IPersonService {
    List<PersonResponseDTO> findAllPersons();
}
