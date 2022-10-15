package com.ayi.curso.rest.serv.app.controller;

import com.ayi.curso.rest.serv.app.dto.response.persons.PersonResponseDTO;
import com.ayi.curso.rest.serv.app.service.IPersonService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;


import java.util.List;

@AllArgsConstructor
@Api(value = "Person Api", tags = {"Person Service"}) //Relacionado con Swagger
@RestController //Para que pueda ser accedido desde afuera de la api. Con controller solo podemos hacerlo de manera interna
@RequestMapping(value = "/persons", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j //Lo vemos más adelante
public class PersonController {
    private IPersonService personService;

    //PutMapping
    //PostMapping
    //DeleteMapping
    @GetMapping(
            value = "/getPersonById/{id}", produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(
            value = "Retrieves all Lists Persons",
            httpMethod = "GET",
            response = PersonResponseDTO[].class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with basic information about persons",
                    response = PersonResponseDTO[].class),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    public ResponseEntity<List<PersonResponseDTO>> getAllPersons() {

        List<PersonResponseDTO> personResponseDTOs = personService.findAllPersons();
        return ResponseEntity.ok(personResponseDTOs);

    }
    @ApiOperation(
            value = "Retrieves data associated to List Master by Id",
            httpMethod = "GET",
            response = PersonResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with basic information for this Lists Master by Id"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    public ResponseEntity<PersonResponseDTO> getPersonById(
            @ApiParam(name = "id", required = true, value = "Person Id", example = "1")
            @PathVariable("id") Long id,
            @ApiParam(name = "nombre", required = true, value = "First Name ", example = "Jesus")
            @PathVariable("nombre") Long nombre) {

        return ResponseEntity.ok(personService.findPersonById(id));

    }

}
