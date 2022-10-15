package com.ayi.spring.rest.serv.app.mappers.impl;

import com.ayi.spring.rest.serv.app.dto.request.client.ClientFullDTO;
import com.ayi.spring.rest.serv.app.dto.request.client.ClientOnlyDTO;
import com.ayi.spring.rest.serv.app.dto.response.client.ClientFullPagesResponseDTO;
import com.ayi.spring.rest.serv.app.dto.response.client.ClientFullResponseDTO;
import com.ayi.spring.rest.serv.app.dto.response.client.ClientOnlyResponseDTO;
import com.ayi.spring.rest.serv.app.entities.Entity_Client;
import com.ayi.spring.rest.serv.app.mappers.Mapp_Client;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class Mapp_Impl_Client implements Mapp_Client {
    private final ModelMapper modelMapper;

    @Override
    public ClientFullResponseDTO entityToFullDto(Entity_Client entity) {
        ClientFullResponseDTO clientFullResponseDTO = new ClientFullResponseDTO();
        modelMapper.map(entity, clientFullResponseDTO);
        return clientFullResponseDTO;
    }

    @Override
    public ClientOnlyResponseDTO entityToOnlyDto(Entity_Client entity) {
        ClientOnlyResponseDTO clientOnlyResponseDTO = new ClientOnlyResponseDTO();
        modelMapper.map(entity, clientOnlyResponseDTO);
        return clientOnlyResponseDTO;
    }

    @Override
    public Entity_Client dtoOnlyToEntity(ClientOnlyDTO dto) {
        Entity_Client entityClient = new Entity_Client();
        modelMapper.map(dto, entityClient);
        return entityClient;
    }

    @Override
    public Entity_Client fullDtoToEntity(ClientFullDTO dto) {
        Entity_Client entityClient = new Entity_Client();
        modelMapper.map(dto, entityClient);

        entityClient.getAddressList().forEach(addressEntity -> {
            addressEntity.setClient(entityClient);
        });

        return entityClient;
    }

    @Override
    public ClientFullPagesResponseDTO entityListToDtoList(List<Entity_Client> entityClientList) {
        ClientFullPagesResponseDTO clientFullPagesResponseDTO = new ClientFullPagesResponseDTO();
        List<ClientFullResponseDTO> clientFullResponseDTOList = new ArrayList<>();

        entityClientList.forEach(clientEntity -> {
            ClientFullResponseDTO clientFullResponseDTO = new ClientFullResponseDTO();
            modelMapper.map(clientEntity, clientFullResponseDTO);
            clientFullResponseDTOList.add(clientFullResponseDTO);
        });

        clientFullPagesResponseDTO.setClientFullResponseDTOList(clientFullResponseDTOList);
        return clientFullPagesResponseDTO;
    }

    @Override
    public Entity_Client toEntityByRequest(ClientOnlyDTO dto) {
        Entity_Client entityClient = new Entity_Client();
        modelMapper.map(dto, entityClient);
        return entityClient;
    }
}
