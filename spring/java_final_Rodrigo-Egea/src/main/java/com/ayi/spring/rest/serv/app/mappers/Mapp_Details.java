package com.ayi.spring.rest.serv.app.mappers;

import com.ayi.spring.rest.serv.app.dto.request.details.DetailsDTO;
import com.ayi.spring.rest.serv.app.dto.response.details.DetailsPagesResponseDTO;
import com.ayi.spring.rest.serv.app.dto.response.details.DetailsWithClientResponseDTO;
import com.ayi.spring.rest.serv.app.entities.Entity_Details;

import java.util.List;

public interface Mapp_Details {
    DetailsWithClientResponseDTO entityToDto(Entity_Details entity);

    Entity_Details dtoToEntity(DetailsDTO dto);

    DetailsPagesResponseDTO entityListToDtoList(List<Entity_Details> entityDetailsList);

    Entity_Details toEntityByRequest(DetailsDTO dto);
}
