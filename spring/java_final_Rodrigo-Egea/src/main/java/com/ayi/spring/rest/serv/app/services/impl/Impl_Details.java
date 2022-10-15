package com.ayi.spring.rest.serv.app.services.impl;

import com.ayi.spring.rest.serv.app.dto.request.details.DetailsDTO;
import com.ayi.spring.rest.serv.app.dto.response.details.DetailsPagesResponseDTO;
import com.ayi.spring.rest.serv.app.dto.response.details.DetailsWithClientResponseDTO;
import com.ayi.spring.rest.serv.app.entities.Entity_Details;
import com.ayi.spring.rest.serv.app.exceptions.RepositoryAccessException;
import com.ayi.spring.rest.serv.app.mappers.Mapp_Details;
import com.ayi.spring.rest.serv.app.repositories.Repo_Details;
import com.ayi.spring.rest.serv.app.services.service_Details;
import com.ayi.spring.rest.serv.app.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ayi.spring.rest.serv.app.constants.ExceptionStrings.READ_ACCESS_EXCEPTION_NOT_FOUND;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class Impl_Details implements service_Details {

    @Autowired
    private Repo_Details detailsRepository;

    @Autowired
    private Mapp_Details detailsMapper;

    Utils utils;

    @Override
    public DetailsPagesResponseDTO findAllDetailsPages(Integer page, Integer size) throws RepositoryAccessException {

        /*List<DetailsWithClientResponseDTO> detailsWithClientResponseDTOList = new ArrayList<>();
        List<Entity_Details> detailsEntityList = detailsRepository.findAll();

        if(detailsEntityList == null) {
            throw new GenericAccessException(READ_ACCESS_EXCEPTION_NOT_FOUND);
        }

        detailsEntityList.forEach(detail -> {
            DetailsWithClientResponseDTO detailsWithClientResponseDTO = detailsMapper.entityToDto(detail);
            detailsWithClientResponseDTOList.add(detailsWithClientResponseDTO);
        });

        return detailsWithClientResponseDTOList;*/

        DetailsPagesResponseDTO detailsPagesResponseDTO;
        Pageable pageable = PageRequest.of(page, size);

        Page<Entity_Details> detailsEntityPages = detailsRepository.findAll(pageable);

        if(detailsEntityPages != null && !detailsEntityPages.isEmpty()) {
            detailsPagesResponseDTO = detailsMapper.entityListToDtoList(detailsEntityPages.getContent());
            detailsPagesResponseDTO.setSize(detailsEntityPages.getSize());
            detailsPagesResponseDTO.setCurrentPage(detailsEntityPages.getNumber() + 1);
            detailsPagesResponseDTO.setTotalPages(detailsEntityPages.getTotalPages());
            detailsPagesResponseDTO.setTotalElements((int) detailsEntityPages.getTotalElements());
            return detailsPagesResponseDTO;
        } else {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_NOT_FOUND);
        }

    }

    @Override
    public DetailsWithClientResponseDTO modifyDetails(Long idDetails, DetailsDTO detailsDTO) throws RepositoryAccessException {

        utils.verifyDetailsId(idDetails);

        Entity_Details entityDetails = detailsRepository.findById(idDetails).get();
        entityDetails.setVipClient(detailsDTO.getVipClient());
        entityDetails.setAccumulatedPoints(detailsDTO.getAccumulatedPoints());
        detailsRepository.save(entityDetails);

        return detailsMapper.entityToDto(entityDetails);

    }

}
