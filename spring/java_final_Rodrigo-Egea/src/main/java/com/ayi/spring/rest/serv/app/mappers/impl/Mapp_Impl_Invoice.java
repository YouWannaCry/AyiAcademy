package com.ayi.spring.rest.serv.app.mappers.impl;

import com.ayi.spring.rest.serv.app.dto.request.invoice.InvoiceWithClientDTO;
import com.ayi.spring.rest.serv.app.dto.request.invoice.InvoiceWithoutClientDTO;
import com.ayi.spring.rest.serv.app.dto.response.invoice.InvoicePagesResponseDTO;
import com.ayi.spring.rest.serv.app.dto.response.invoice.InvoiceWithFullClientDataResponseDTO;
import com.ayi.spring.rest.serv.app.dto.response.invoice.InvoiceWithClientResponseDTO;
import com.ayi.spring.rest.serv.app.entities.Entity_Invoice;
import com.ayi.spring.rest.serv.app.mappers.Mapp_Invoice;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class Mapp_Impl_Invoice implements Mapp_Invoice {
    private final ModelMapper modelMapper;

    @Override
    public InvoiceWithFullClientDataResponseDTO entityToDto(Entity_Invoice entity) {
        InvoiceWithFullClientDataResponseDTO invoiceWithFullClientDataResponseDTO = new InvoiceWithFullClientDataResponseDTO();
        modelMapper.map(entity, invoiceWithFullClientDataResponseDTO);
        return invoiceWithFullClientDataResponseDTO;
    }

    @Override
    public InvoiceWithClientResponseDTO entityToDtoSimple(Entity_Invoice entity) {
        InvoiceWithClientResponseDTO invoiceWithClientResponseDTO = new InvoiceWithClientResponseDTO();
        modelMapper.map(entity, invoiceWithClientResponseDTO);
        return invoiceWithClientResponseDTO;
    }

    @Override
    public Entity_Invoice dtoWithToEntity(InvoiceWithClientDTO dto) {
        Entity_Invoice entityInvoice = new Entity_Invoice();
        modelMapper.map(dto, entityInvoice);
        return entityInvoice;
    }

    @Override
    public Entity_Invoice dtoWithoutToEntity(InvoiceWithoutClientDTO dto) {
        Entity_Invoice entityInvoice = new Entity_Invoice();
        modelMapper.map(dto, entityInvoice);
        return entityInvoice;
    }

    @Override
    public InvoicePagesResponseDTO entityListToDtoList(List<Entity_Invoice> entityInvoiceList) {
        InvoicePagesResponseDTO invoicePagesResponseDTO = new InvoicePagesResponseDTO();
        List<InvoiceWithClientResponseDTO> invoiceWithClientResponseDTOList = new ArrayList<>();

        entityInvoiceList.forEach(invoiceEntity -> {
            InvoiceWithClientResponseDTO invoiceWithClientResponseDTO = new InvoiceWithClientResponseDTO();
            modelMapper.map(invoiceEntity, invoiceWithClientResponseDTO);
            invoiceWithClientResponseDTOList.add(invoiceWithClientResponseDTO);
        });

        invoicePagesResponseDTO.setInvoiceWithClientResponseDTOList(invoiceWithClientResponseDTOList);
        return invoicePagesResponseDTO;
    }

    @Override
    public Entity_Invoice toEntityByRequest(InvoiceWithClientDTO dto) {
        Entity_Invoice entityInvoice = new Entity_Invoice();
        modelMapper.map(dto, entityInvoice);
        return entityInvoice;
    }
}
