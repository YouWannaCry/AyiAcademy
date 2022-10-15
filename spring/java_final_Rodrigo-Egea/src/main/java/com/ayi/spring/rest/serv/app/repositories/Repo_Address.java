package com.ayi.spring.rest.serv.app.repositories;

import com.ayi.spring.rest.serv.app.entities.Entity_Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repo_Address extends JpaRepository<Entity_Address, Long> {
}
