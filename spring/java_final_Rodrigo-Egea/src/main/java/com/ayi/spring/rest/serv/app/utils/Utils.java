package com.ayi.spring.rest.serv.app.utils;

import com.ayi.spring.rest.serv.app.entities.Entity_Address;
import com.ayi.spring.rest.serv.app.entities.Entity_Client;
import com.ayi.spring.rest.serv.app.entities.Entity_Details;
import com.ayi.spring.rest.serv.app.entities.Entity_Invoice;
import com.ayi.spring.rest.serv.app.exceptions.RepositoryAccessException;
import com.ayi.spring.rest.serv.app.repositories.Repo_Address;
import com.ayi.spring.rest.serv.app.repositories.Repo_Client;
import com.ayi.spring.rest.serv.app.repositories.Repo_Details;
import com.ayi.spring.rest.serv.app.repositories.Repo_Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.ayi.spring.rest.serv.app.constants.ExceptionStrings.*;

@Service
public class Utils {

    @Autowired
    Repo_Client clientRepository;

    @Autowired
    Repo_Invoice invoiceRepository;

    @Autowired
    Repo_Address addressRepository;

    @Autowired
    Repo_Details detailsRepository;

    /**
     *
     * Function to verify the integrity or existence of the client ID provided
     *
     * */
    public void verifyClientId(Long idClient) throws RepositoryAccessException {
        if(idClient == null || idClient <= 0) {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_INCORRECT_INPUT);
        }

        Optional<Entity_Client> entity = clientRepository.findById(idClient);

        if(!entity.isPresent()) {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_ID_NOT_FOUND);
        }
    }

    /**
     *
     * Function to verify the integrity or existence of the invoice ID provided
     *
     * */
    public void verifyInvoiceId(Long idInvoice) throws RepositoryAccessException {
        if(idInvoice == null || idInvoice <= 0) {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_INCORRECT_INPUT);
        }

        Optional<Entity_Invoice> entity = invoiceRepository.findById(idInvoice);

        if(!entity.isPresent()) {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_ID_NOT_FOUND);
        }
    }

    /**
     *
     * Function to verify the integrity or existence of the address ID provided
     *
     * */
    public void verifyAddressId(Long idAddress) throws RepositoryAccessException {
        if(idAddress == null || idAddress <= 0) {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_INCORRECT_INPUT);
        }

        Optional<Entity_Address> entity = addressRepository.findById(idAddress);

        if(!entity.isPresent()) {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_ID_NOT_FOUND);
        }
    }

    /**
     *
     * Function to verify the integrity or existence of the details ID provided
     *
     * */
    public void verifyDetailsId(Long idDetails) throws RepositoryAccessException {
        if(idDetails == null || idDetails <= 0) {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_INCORRECT_INPUT);
        }

        Optional<Entity_Details> entity = detailsRepository.findById(idDetails);

        if(!entity.isPresent()) {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_ID_NOT_FOUND);
        }
    }

    /**
     *
     * Function to verify the integrity or existence of the client address ID provided.
     * The ID Client must be verified before using this method
     *
     * */
    public void verifyClientAddressId(Long idClient, Long idAddress) throws RepositoryAccessException {

        if(idAddress == null || idClient == null || idAddress <= 0 || idClient <= 0) {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_INCORRECT_INPUT);
        }

        Optional<Entity_Address> addressEntity = addressRepository.findById(idAddress);

        if(!addressEntity.isPresent()) {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_ID_NOT_FOUND);
        } else if(addressEntity.get().getClient().getIdClient() != idClient) {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_CLIENT_ADDRESS_NOT_FOUND);
        }

    }

    /**
     *
     * Function to verify existence of the DNI provided
     *
     * */
    public void verifyClientDni(String dni) throws RepositoryAccessException {
        Optional<Entity_Client> clientOptional = clientRepository.findByDni(dni);

        if(clientOptional.isPresent()) {
            throw new RepositoryAccessException(WRITE_ACCESS_EXCEPTION_DNI);
        }
    }

}
