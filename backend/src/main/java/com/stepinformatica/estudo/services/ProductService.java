package com.stepinformatica.estudo.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stepinformatica.estudo.dto.ProductDTO;
import com.stepinformatica.estudo.entities.Product;
import com.stepinformatica.estudo.repositories.ProductRepository;
import com.stepinformatica.estudo.services.exceptions.DatabaseException;
import com.stepinformatica.estudo.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	/*
	 * @Autowired private CategoryRepository repository;
	 */

	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
		Page<Product> list = repository.findAll(pageRequest);
		return list.map(x -> new ProductDTO(x));
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setImgUrl(dto.getImgUrl());
		entity.setPrice(dto.getPrice());
		entity = repository.save(entity);
		return new ProductDTO(entity);
	}

	@Transactional
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}

	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = repository.getOne(id);
			entity.setName(dto.getName());
			entity.setDescription(dto.getDescription());
			entity.setImgUrl(dto.getImgUrl());
			entity.setPrice(dto.getPrice());
			entity = repository.save(entity);
			return new ProductDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}


	public ProductDTO findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		Product entity = obj.get();
		return new ProductDTO(entity);
	}
}
