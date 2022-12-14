package cl.cokke.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.cokke.dto.LoginDTO;
import cl.cokke.exception.BadRecuestException;
import cl.cokke.model.User;
import cl.cokke.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
		//System.out.println(username);
		//System.out.println(password);
		String token = userService.login(loginDTO.getUsername(), loginDTO.getPassword());
		return ResponseEntity.ok().body(token);
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> crearUsuario(@RequestBody User User){
		//System.out.println(User);
		userService.crearUser(User);
		System.out.println("Role: " + User.getRoles());
		return ResponseEntity.ok().body(User);
	}
	
	
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		if(userService.findAll().isEmpty()) {
			throw new BadRecuestException(" No se encontraron usuarios");
		}else {
			return ResponseEntity.ok().body(userService.findAll());
		}

	}

}
