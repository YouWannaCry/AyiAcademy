package com.ayi.spring.rest.serv.app.mappers.impl;

import com.ayi.spring.rest.serv.app.dto.request.details.DetailsDTO;
import com.ayi.spring.rest.serv.app.dto.response.details.DetailsPagesResponseDTO;
import com.ayi.spring.rest.serv.app.dto.response.details.DetailsWithClientResponseDTO;
import com.ayi.spring.rest.serv.app.entities.Entity_Details;
import com.ayi.spring.rest.serv.app.mappers.Mapp_Details;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class Mapp_Impl_Details implements Mapp_Details {
    private final ModelMapper modelMapper;

    @Override
    public DetailsWithClientResponseDTO entityToDto(Entity_Details entity) {
        DetailsWithClientResponseDTO detailsWithClientResponseDTO = new DetailsWithClientResponseDTO();
        modelMapper.map(entity, detailsWithClientResponseDTO);
        return detailsWithClientResponseDTO;
    }

    @Override
    public Entity_Details dtoToEntity(DetailsDTO dto) {
        Entity_Details entityDetails = new Entity_Details();
        modelMapper.map(dto, entityDetails);
        return entityDetails;
    }

    @Override
    public DetailsPagesResponseDTO entityListToDtoList(List<Entity_Details> entityDetailsList) {
        DetailsPagesResponseDTO detailsPagesResponseDTO = new DetailsPagesResponseDTO();
        List<DetailsWithClientResponseDTO> detailsWithClientResponseDTOList = new ArrayList<>();

        entityDetailsList.forEach(detailsEntity -> {
            DetailsWithClientResponseDTO detailsWithClientResponseDTO = new DetailsWithClientResponseDTO();
            modelMapper.map(detailsEntity, detailsWithClientResponseDTO);
            detailsWithClientResponseDTOList.add(detailsWithClientResponseDTO);
        });

        detailsPagesResponseDTO.setDetailsWithClientResponseDTOList(detailsWithClientResponseDTOList);
        return detailsPagesResponseDTO;
    }

    @Override
    public Entity_Details toEntityByRequest(DetailsDTO dto) {
        Entity_Details entityDetails = new Entity_Details();
        modelMapper.map(dto, entityDetails);
        return entityDetails;
    }
}
