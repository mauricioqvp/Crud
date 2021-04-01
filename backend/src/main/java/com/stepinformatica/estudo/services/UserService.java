package com.stepinformatica.estudo.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.stepinformatica.estudo.dto.UserDTO;
import com.stepinformatica.estudo.dto.UserInsertDTO;
import com.stepinformatica.estudo.entities.User;
import com.stepinformatica.estudo.repositories.UserRepository;
import com.stepinformatica.estudo.services.exceptions.DatabaseException;
import com.stepinformatica.estudo.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public UserDTO insert(UserInsertDTO dto) {
		User entity = new User();
		entity = CopyDtoToEntity(dto, entity);
		entity.setPassword(passwordEncoder.encode(dto.getPassword()));
		entity = userRepository.save(entity);
		return new UserDTO(entity);
	}

	public UserDTO findById(Long id) {
		Optional<User> obj = userRepository.findById(id);
		User user = obj.orElseThrow(() -> new ResourceNotFoundException("Item não encontrado"));
		return new UserDTO(user);
	}

	public Page<UserDTO> findAllPaged(PageRequest pageRequest) {
		Page<User> list = userRepository.findAll(pageRequest);
		return list.map(x -> new UserDTO(x));
	}

	@Transactional
	public UserDTO upDate(Long id, UserDTO dto) {
		try {
			User entity = userRepository.getOne(id);
			CopyDtoToEntity(dto, entity);
			entity = userRepository.save(entity);
			return new UserDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Item não encontrado");
		}
	}

	public void delete(@PathVariable Long id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw (new ResourceNotFoundException("Id not found: " + id));
		} catch (DataIntegrityViolationException e) {
			throw (new DatabaseException("Integrity violation"));
		}
	}

	public User CopyDtoToEntity(UserDTO dto, User entity) {
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setEmail(dto.getEmail());
		return entity;
	}
}
