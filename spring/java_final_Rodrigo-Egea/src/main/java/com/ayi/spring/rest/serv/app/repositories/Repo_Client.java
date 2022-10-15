package com.ayi.spring.rest.serv.app.repositories;


import com.ayi.spring.rest.serv.app.entities.Entity_Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Repo_Client extends JpaRepository<Entity_Client, Long> {

    @Query("Select CE from Entity_Client CE where CE.dni = :dni")
    Optional<Entity_Client> findByDni(@Param("dni") String dni);

}
