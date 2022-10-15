package com.ayi.spring.rest.serv.app.mappers;

import com.ayi.spring.rest.serv.app.dto.request.address.AddressDTO;
import com.ayi.spring.rest.serv.app.dto.response.address.AddressPagesResponseDTO;
import com.ayi.spring.rest.serv.app.dto.response.address.AddressResponseDTO;
import com.ayi.spring.rest.serv.app.entities.Entity_Address;

import java.util.List;

public interface Mapp_Address {
    AddressResponseDTO entityToDto(Entity_Address entity);

    Entity_Address dtoToEntity(AddressDTO dto);

    AddressPagesResponseDTO entityListToDtoList(List<Entity_Address> entityAddressList);

    Entity_Address toEntityByRequest(AddressDTO dto);
}
