package com.stepinformatica.estudo.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stepinformatica.estudo.dto.ProductDTO;
import com.stepinformatica.estudo.entities.Category;
import com.stepinformatica.estudo.entities.Product;
import com.stepinformatica.estudo.repositories.CategoryRepository;
import com.stepinformatica.estudo.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(Long categoryId, String name, PageRequest pageRequest) {
		List<Category> categories = (categoryId == 0) ? null : Arrays.asList(categoryRepository.getOne(categoryId));
		Page<Product> list = repository.find(categories, name,  pageRequest);
		return list.map(x -> new ProductDTO(x));
	}
}
