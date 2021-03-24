package com.stepinformatica.estudo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stepinformatica.estudo.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
