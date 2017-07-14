package pl.pils.dw.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.pils.dw.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/user")
	public List<pl.pils.dw.dto.User> getUsers(){
		
		return this.userService.getUsersDTO();
	}
	
	@RequestMapping("/user/{id}")
	public pl.pils.dw.dto.User getUser(@PathVariable Long id){
		
		return this.userService.getUserDTO(id);
	}

}
