package com.stepinformatica.estudo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stepinformatica.estudo.dto.ProductDTO;
import com.stepinformatica.estudo.entities.Product;
import com.stepinformatica.estudo.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	/*
	 * @Autowired private CategoryRepository repository;
	 */
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged( PageRequest pageRequest) {
		Page<Product> list = repository.findAll(pageRequest);
		return list.map(x -> new ProductDTO(x));
	}
}
