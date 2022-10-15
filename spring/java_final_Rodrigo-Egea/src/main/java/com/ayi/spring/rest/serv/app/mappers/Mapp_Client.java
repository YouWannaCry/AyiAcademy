package com.ayi.spring.rest.serv.app.mappers;

import com.ayi.spring.rest.serv.app.dto.request.client.ClientFullDTO;
import com.ayi.spring.rest.serv.app.dto.request.client.ClientOnlyDTO;
import com.ayi.spring.rest.serv.app.dto.response.client.ClientFullPagesResponseDTO;
import com.ayi.spring.rest.serv.app.dto.response.client.ClientFullResponseDTO;
import com.ayi.spring.rest.serv.app.dto.response.client.ClientOnlyResponseDTO;
import com.ayi.spring.rest.serv.app.entities.Entity_Client;

import java.util.List;

public interface Mapp_Client {
    ClientFullResponseDTO entityToFullDto(Entity_Client entity);

    ClientOnlyResponseDTO entityToOnlyDto(Entity_Client entity);

    Entity_Client dtoOnlyToEntity(ClientOnlyDTO dto);

    Entity_Client fullDtoToEntity(ClientFullDTO dto);

    ClientFullPagesResponseDTO entityListToDtoList(List<Entity_Client> entityClientList);

    Entity_Client toEntityByRequest(ClientOnlyDTO dto);
}
