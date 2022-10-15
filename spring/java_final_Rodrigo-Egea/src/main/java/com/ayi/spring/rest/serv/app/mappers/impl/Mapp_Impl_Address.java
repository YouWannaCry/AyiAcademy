package com.ayi.spring.rest.serv.app.mappers.impl;

import com.ayi.spring.rest.serv.app.dto.request.address.AddressDTO;
import com.ayi.spring.rest.serv.app.dto.response.address.AddressPagesResponseDTO;
import com.ayi.spring.rest.serv.app.dto.response.address.AddressResponseDTO;
import com.ayi.spring.rest.serv.app.entities.Entity_Address;
import com.ayi.spring.rest.serv.app.mappers.Mapp_Address;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class Mapp_Impl_Address implements Mapp_Address {
    private final ModelMapper modelMapper;

    @Override
    public AddressResponseDTO entityToDto(Entity_Address entity) {
        AddressResponseDTO addressResponseDTO = new AddressResponseDTO();
        modelMapper.map(entity, addressResponseDTO);
        return addressResponseDTO;
    }

    @Override
    public Entity_Address dtoToEntity(AddressDTO dto) {
        Entity_Address entityAddress = new Entity_Address();
        modelMapper.map(dto, entityAddress);
        return entityAddress;
    }

    @Override
    public AddressPagesResponseDTO entityListToDtoList(List<Entity_Address> entityAddressList) {
        AddressPagesResponseDTO addressPagesResponseDTO = new AddressPagesResponseDTO();
        List<AddressResponseDTO> addressResponseDTOList = new ArrayList<>();

        entityAddressList.forEach(addressEntity -> {
            AddressResponseDTO addressResponseDTO = new AddressResponseDTO();
            modelMapper.map(addressEntity, addressResponseDTO);
            addressResponseDTOList.add(addressResponseDTO);
        });

        addressPagesResponseDTO.setAddressResponseDTOList(addressResponseDTOList);
        return addressPagesResponseDTO;
    }

    @Override
    public Entity_Address toEntityByRequest(AddressDTO dto) {
        Entity_Address entityAddress = new Entity_Address();
        modelMapper.map(dto, entityAddress);
        return entityAddress;
    }
}
