package com.ayi.spring.rest.serv.app.services.impl;

import com.ayi.spring.rest.serv.app.dto.request.client.ClientFullDTO;
import com.ayi.spring.rest.serv.app.dto.request.client.ClientOnlyDTO;
import com.ayi.spring.rest.serv.app.dto.response.client.*;
import com.ayi.spring.rest.serv.app.entities.Entity_Client;
import com.ayi.spring.rest.serv.app.exceptions.RepositoryAccessException;
import com.ayi.spring.rest.serv.app.mappers.Mapp_Client;
import com.ayi.spring.rest.serv.app.repositories.Repo_Client;
import com.ayi.spring.rest.serv.app.repositories.Repo_Invoice;
import com.ayi.spring.rest.serv.app.services.service_Client;
import com.ayi.spring.rest.serv.app.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ayi.spring.rest.serv.app.constants.ExceptionStrings.*;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class Impl_Client implements service_Client {

    @Autowired
    private Repo_Client clientRepository;

    @Autowired
    private Repo_Invoice invoiceRepository;

    @Autowired
    private Mapp_Client clientMapper;

    private Utils utils;

    @Override
    public ClientFullResponseDTO addClient(ClientFullDTO clientFullDTO) throws RepositoryAccessException {

        utils.verifyClientDni(clientFullDTO.getDni());

        Entity_Client entityClient = clientMapper.fullDtoToEntity(clientFullDTO);
        entityClient.setIsActive(true);

        clientRepository.save(entityClient);

        return clientMapper.entityToFullDto(entityClient);

    }

    @Override
    public ClientFullPagesResponseDTO findAllClients(Integer page, Integer size) throws RepositoryAccessException {

        ClientFullPagesResponseDTO clientFullPagesResponseDTO;
        Pageable pageable = PageRequest.of(page, size);

        Page<Entity_Client> clientEntityPages = clientRepository.findAll(pageable);

        if(clientEntityPages != null && !clientEntityPages.isEmpty()) {
            clientFullPagesResponseDTO = clientMapper.entityListToDtoList(clientEntityPages.getContent());
            clientFullPagesResponseDTO.setSize(clientEntityPages.getSize());
            clientFullPagesResponseDTO.setCurrentPage(clientEntityPages.getNumber() + 1);
            clientFullPagesResponseDTO.setTotalPages(clientEntityPages.getTotalPages());
            clientFullPagesResponseDTO.setTotalElements((int) clientEntityPages.getTotalElements());
            return clientFullPagesResponseDTO;
        } else {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_NOT_FOUND);
        }

    }

    @Override
    public ClientFullResponseDTO findClientById(Long idClient) throws RepositoryAccessException {

        utils.verifyClientId(idClient);

        Entity_Client entityClient = clientRepository.findById(idClient).get();

        return clientMapper.entityToFullDto(entityClient);

    }

    @Override
    public ClientInvoicesResponseDTO findClientInvoices(Long idClient) throws RepositoryAccessException {

        utils.verifyClientId(idClient);

        Entity_Client entityClient = clientRepository.findById(idClient).get();
        ClientFullResponseDTO clientFullResponseDTO = clientMapper.entityToFullDto(entityClient);

        ClientInvoicesResponseDTO clientInvoicesResponseDTO = new ClientInvoicesResponseDTO(
                idClient,
                clientFullResponseDTO.getDni(),
                clientFullResponseDTO.getFirstName(),
                clientFullResponseDTO.getLastName(),
                clientFullResponseDTO.getInvoiceList()
        );

        return clientInvoicesResponseDTO;

    }

    @Override
    public ClientOnlyResponseDTO modifyClient(Long idClient, ClientOnlyDTO clientOnlyDTO) throws RepositoryAccessException {

        utils.verifyClientId(idClient);
        utils.verifyClientDni(clientOnlyDTO.getDni());

        Entity_Client entityClient = clientRepository.findById(idClient).get();

        entityClient.setDni(clientOnlyDTO.getDni());
        entityClient.setFirstName(clientOnlyDTO.getFirstName());
        entityClient.setLastName(clientOnlyDTO.getLastName());

        clientRepository.save(entityClient);

        return clientMapper.entityToOnlyDto(entityClient);

    }

    @Override
    public ClientFullResponseDTO removeClient(Long idClient) throws RepositoryAccessException {

        utils.verifyClientId(idClient);

        Entity_Client entityClient = clientRepository.findById(idClient).get();

        entityClient.setIsActive(false);
        clientRepository.save(entityClient);
        ClientFullResponseDTO clientFullResponseDTO = clientMapper.entityToFullDto(entityClient);

        return clientFullResponseDTO;
    }

}
