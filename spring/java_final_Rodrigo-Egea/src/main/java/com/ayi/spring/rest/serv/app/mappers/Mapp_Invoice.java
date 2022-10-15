package com.ayi.spring.rest.serv.app.mappers;

import com.ayi.spring.rest.serv.app.dto.request.invoice.InvoiceWithClientDTO;
import com.ayi.spring.rest.serv.app.dto.request.invoice.InvoiceWithoutClientDTO;
import com.ayi.spring.rest.serv.app.dto.response.invoice.InvoicePagesResponseDTO;
import com.ayi.spring.rest.serv.app.dto.response.invoice.InvoiceWithFullClientDataResponseDTO;
import com.ayi.spring.rest.serv.app.dto.response.invoice.InvoiceWithClientResponseDTO;
import com.ayi.spring.rest.serv.app.entities.Entity_Invoice;

import java.util.List;

public interface Mapp_Invoice {
    InvoiceWithFullClientDataResponseDTO entityToDto(Entity_Invoice entity);

    InvoiceWithClientResponseDTO entityToDtoSimple(Entity_Invoice entity);

    Entity_Invoice dtoWithToEntity(InvoiceWithClientDTO dto);

    Entity_Invoice dtoWithoutToEntity(InvoiceWithoutClientDTO dto);

    InvoicePagesResponseDTO entityListToDtoList(List<Entity_Invoice> entityInvoiceList);

    Entity_Invoice toEntityByRequest(InvoiceWithClientDTO dto);
}
