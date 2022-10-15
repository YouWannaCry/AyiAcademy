package com.ayi.spring.rest.serv.app.services;

import com.ayi.spring.rest.serv.app.dto.request.details.DetailsDTO;
import com.ayi.spring.rest.serv.app.dto.response.details.DetailsPagesResponseDTO;
import com.ayi.spring.rest.serv.app.dto.response.details.DetailsWithClientResponseDTO;
import com.ayi.spring.rest.serv.app.exceptions.RepositoryAccessException;

public interface service_Details {

    DetailsPagesResponseDTO findAllDetailsPages(Integer page, Integer size) throws RepositoryAccessException;

    DetailsWithClientResponseDTO modifyDetails(Long id, DetailsDTO detailsDTO) throws RepositoryAccessException;
}
