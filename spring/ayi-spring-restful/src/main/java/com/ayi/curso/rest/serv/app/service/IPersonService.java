package com.ayi.curso.rest.serv.app.service;


import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTO;

import java.util.List;
public interface IPersonService {
    List<PersonResponseDTO> findAllPersons();

    PersonResponseDTO findPersonById(Long idPerson);
}
