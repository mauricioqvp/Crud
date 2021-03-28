package com.stepinformatica.estudo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stepinformatica.estudo.dto.UserDTO;
import com.stepinformatica.estudo.dto.UserInsertDTO;
import com.stepinformatica.estudo.services.UserService;

@Controller
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService userService;

	public ResponseEntity<UserDTO> insert(UserInsertDTO entity) {
		UserDTO dto = userService.insert(entity);
		return ResponseEntity.ok().body(dto);
	}
}
