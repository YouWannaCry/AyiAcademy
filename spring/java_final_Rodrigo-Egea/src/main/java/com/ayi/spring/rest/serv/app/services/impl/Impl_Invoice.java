package com.ayi.spring.rest.serv.app.services.impl;

import com.ayi.spring.rest.serv.app.dto.request.invoice.InvoiceWithClientDTO;
import com.ayi.spring.rest.serv.app.dto.request.invoice.InvoiceWithoutClientDTO;
import com.ayi.spring.rest.serv.app.dto.response.invoice.InvoicePagesResponseDTO;
import com.ayi.spring.rest.serv.app.dto.response.invoice.InvoiceWithFullClientDataResponseDTO;
import com.ayi.spring.rest.serv.app.dto.response.invoice.InvoiceWithClientResponseDTO;
import com.ayi.spring.rest.serv.app.entities.Entity_Client;
import com.ayi.spring.rest.serv.app.entities.Entity_Invoice;
import com.ayi.spring.rest.serv.app.exceptions.RepositoryAccessException;
import com.ayi.spring.rest.serv.app.mappers.Mapp_Invoice;
import com.ayi.spring.rest.serv.app.repositories.Repo_Client;
import com.ayi.spring.rest.serv.app.repositories.Repo_Invoice;
import com.ayi.spring.rest.serv.app.services.service_Invoice;
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
public class Impl_Invoice implements service_Invoice {

    @Autowired
    private Repo_Invoice invoiceRepository;

    @Autowired
    private Repo_Client clientRepository;

    @Autowired
    private Mapp_Invoice invoiceMapper;

    private Utils utils;

    @Override
    public InvoiceWithClientResponseDTO addInvoiceWithoutClient(Long idClient, InvoiceWithoutClientDTO invoiceWithoutClientDTO) throws RepositoryAccessException {

        utils.verifyClientId(idClient);

        Entity_Invoice entityInvoice = invoiceMapper.dtoWithoutToEntity(invoiceWithoutClientDTO);
        Entity_Client entityClient = clientRepository.findById(idClient).get();

        entityInvoice.setClient(entityClient);
        invoiceRepository.save(entityInvoice);

        return invoiceMapper.entityToDtoSimple(entityInvoice);

    }

    @Override
    public InvoiceWithFullClientDataResponseDTO addInvoiceWithClient(InvoiceWithClientDTO invoiceWithClientDTO) throws RepositoryAccessException {

        utils.verifyClientDni(invoiceWithClientDTO.getClient().getDni());

        Entity_Invoice entityInvoice = invoiceMapper.dtoWithToEntity(invoiceWithClientDTO);
        entityInvoice.getClient().setIsActive(true);

        invoiceRepository.save(entityInvoice);

        return invoiceMapper.entityToDto(entityInvoice);

    }

    @Override
    public InvoicePagesResponseDTO findAllInvoicePages(Integer page, Integer size) throws RepositoryAccessException {

        /*List<InvoiceWithClientResponseDTO> invoiceWithClientResponseDTOList = new ArrayList<>();
        List<Entity_Invoice> invoiceEntityList = invoiceRepository.findAll();

        if(invoiceEntityList == null) {
            throw new GenericAccessException(READ_ACCESS_EXCEPTION_NOT_FOUND);
        }

        invoiceEntityList.forEach(invoice -> {
            InvoiceWithClientResponseDTO invoiceWithClientResponseDTO = invoiceMapper.entityToDtoSimple(invoice);
            invoiceWithClientResponseDTOList.add(invoiceWithClientResponseDTO);
        });

        return invoiceWithClientResponseDTOList;*/

        InvoicePagesResponseDTO invoicePagesResponseDTO;
        Pageable pageable = PageRequest.of(page, size);

        Page<Entity_Invoice> invoiceEntityPages = invoiceRepository.findAll(pageable);

        if(invoiceEntityPages != null && !invoiceEntityPages.isEmpty()) {
            invoicePagesResponseDTO = invoiceMapper.entityListToDtoList(invoiceEntityPages.getContent());
            invoicePagesResponseDTO.setSize(invoiceEntityPages.getSize());
            invoicePagesResponseDTO.setCurrentPage(invoiceEntityPages.getNumber() + 1);
            invoicePagesResponseDTO.setTotalPages(invoiceEntityPages.getTotalPages());
            invoicePagesResponseDTO.setTotalElements((int) invoiceEntityPages.getTotalElements());
            return invoicePagesResponseDTO;
        } else {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_NOT_FOUND);
        }

    }

    @Override
    public InvoiceWithClientResponseDTO findInvoiceById(Long idInvoice) throws RepositoryAccessException {

        utils.verifyInvoiceId(idInvoice);

        Entity_Invoice entityInvoice = invoiceRepository.findById(idInvoice).get();

        return invoiceMapper.entityToDtoSimple(entityInvoice);

    }


    @Override
    public InvoiceWithClientResponseDTO removeInvoice(Long idInvoice) throws RepositoryAccessException {

        utils.verifyInvoiceId(idInvoice);

        Entity_Invoice entityInvoice = invoiceRepository.findById(idInvoice).get();
        InvoiceWithClientResponseDTO invoiceWithClientResponseDTO = invoiceMapper.entityToDtoSimple(entityInvoice);

        invoiceRepository.deleteById(idInvoice);

        return invoiceWithClientResponseDTO;

    }

}
