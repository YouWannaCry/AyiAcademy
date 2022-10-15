package com.ayi.spring.rest.serv.app.repositories;

import com.ayi.spring.rest.serv.app.entities.Entity_Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repo_Invoice extends JpaRepository<Entity_Invoice, Long> {
}
