package com.stepinformatica.estudo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stepinformatica.estudo.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
