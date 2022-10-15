package com.ayi.curso.rest.serv.app.repositories;

import com.ayi.curso.rest.serv.app.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends JpaRepository<PersonEntity, Long> {
}
