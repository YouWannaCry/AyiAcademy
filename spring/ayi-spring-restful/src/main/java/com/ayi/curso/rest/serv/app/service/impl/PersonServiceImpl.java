package com.ayi.curso.rest.serv.app.service.impl;

import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTO;
import com.ayi.curso.rest.serv.app.entity.PersonEntity;
import com.ayi.curso.rest.serv.app.mapper.IPersonMapper;
import com.ayi.curso.rest.serv.app.repository.IPersonRepository;
import com.ayi.curso.rest.serv.app.service.IPersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service //Para que pueda ser inyectado desde otro lugar
@Slf4j
@Transactional //Hace el trabajo de JPA (commit - begin - rollback - etc)
public class PersonServiceImpl extends Exception implements IPersonService {

        @Autowired
        private IPersonRepository personRepository;

        @Autowired
        private IPersonMapper personMapper;

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
                                lt.getDateBorn(),
                                lt.getNumberDocument(),
                                lt.getDateCreated(),
                                lt.getDateModified()
                        ))
                        .collect(Collectors.toList());

                return personResponseDTOs;
        }

        @Override
        public PersonResponseDTO findPersonById(Long idPerson) {

                PersonResponseDTO personResponseDTO;

                Optional<PersonEntity> entity = personRepository.findById(idPerson);

                if (!entity.isPresent()) {
                        throw new RuntimeException("Error no existe el id de persona buscado");
                }

                personResponseDTO = personMapper.entityToDto(entity.get());
                return personResponseDTO;

        }
}
