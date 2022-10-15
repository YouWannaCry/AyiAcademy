package com.ayi.spring.rest.serv.app.services.impl;

import com.ayi.spring.rest.serv.app.dto.request.address.AddressDTO;
import com.ayi.spring.rest.serv.app.dto.response.address.AddressPagesResponseDTO;
import com.ayi.spring.rest.serv.app.dto.response.address.AddressResponseDTO;
import com.ayi.spring.rest.serv.app.entities.Entity_Address;
import com.ayi.spring.rest.serv.app.entities.Entity_Client;
import com.ayi.spring.rest.serv.app.exceptions.RepositoryAccessException;
import com.ayi.spring.rest.serv.app.mappers.Mapp_Address;
import com.ayi.spring.rest.serv.app.repositories.Repo_Address;
import com.ayi.spring.rest.serv.app.repositories.Repo_Client;
import com.ayi.spring.rest.serv.app.services.service_Address;
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
public class Impl_Address implements service_Address {

    @Autowired
    Repo_Address addressRepository;

    @Autowired
    Repo_Client clientRepository;

    @Autowired
    Mapp_Address addressMapper;

    Utils utils;

    @Override
    public AddressResponseDTO addAddress(Long idClient, AddressDTO addressDTO) throws RepositoryAccessException {

        utils.verifyClientId(idClient);

        Entity_Address entityAddress = addressMapper.dtoToEntity(addressDTO);
        Entity_Client entityClient = clientRepository.findById(idClient).get();
        entityAddress.setClient(entityClient);

        addressRepository.save(entityAddress);

        return addressMapper.entityToDto(entityAddress);

    }

    @Override
    public AddressPagesResponseDTO findAllAddressPages(Integer page, Integer size) throws RepositoryAccessException {

        AddressPagesResponseDTO addressPagesResponseDTO;
        Pageable pageable = PageRequest.of(page, size);

        Page<Entity_Address> addressEntityPages = addressRepository.findAll(pageable);

        if(addressEntityPages != null && !addressEntityPages.isEmpty()) {
            addressPagesResponseDTO = addressMapper.entityListToDtoList(addressEntityPages.getContent());
            addressPagesResponseDTO.setSize(addressEntityPages.getSize());
            addressPagesResponseDTO.setCurrentPage(addressEntityPages.getNumber() + 1);
            addressPagesResponseDTO.setTotalPages(addressEntityPages.getTotalPages());
            addressPagesResponseDTO.setTotalElements((int) addressEntityPages.getTotalElements());
            return addressPagesResponseDTO;
        } else {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_NOT_FOUND);
        }

    }

    @Override
    public AddressResponseDTO findAddressById(Long idAddress) throws RepositoryAccessException {

        utils.verifyAddressId(idAddress);

        Entity_Address entityAddress = addressRepository.findById(idAddress).get();

        return addressMapper.entityToDto(entityAddress);

    }

    @Override
    public AddressResponseDTO modifyAddress(Long idClient, Long idAddress, AddressDTO addressDTO) throws RepositoryAccessException {

        utils.verifyClientId(idClient);
        utils.verifyClientAddressId(idClient, idAddress);

        Entity_Address entityAddress = addressMapper.dtoToEntity(addressDTO);
        entityAddress.setIdAddress(idAddress);
        entityAddress.setClient(clientRepository.findById(idClient).get());
        addressRepository.save(entityAddress);

        return addressMapper.entityToDto(entityAddress);
    }

    @Override
    public AddressResponseDTO removeAddress(Long idClient, Long idAddress) throws RepositoryAccessException {

        utils.verifyClientId(idClient);
        utils.verifyClientAddressId(idClient, idAddress);

        Entity_Address entityAddress = addressRepository.findById(idAddress).get();
        AddressResponseDTO addressResponseDTO = addressMapper.entityToDto(entityAddress);

        addressRepository.delete(entityAddress);

        return addressResponseDTO;

    }

}
