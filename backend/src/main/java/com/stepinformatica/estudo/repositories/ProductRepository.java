package com.stepinformatica.estudo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stepinformatica.estudo.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
