package com.stepinformatica.estudo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import com.stepinformatica.estudo.entities.User;

@RestController
public interface UserRepository extends JpaRepository<User, Long>{

}
