package test.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/user")
	public List<test.dto.User> getUsers(){
		
		return this.userService.getUsersDTO();
	}
	
	@RequestMapping("/user/{id}")
	public test.dto.User getUser(@PathVariable Long id){
		
		return this.userService.getUserDTO(id);
	}

}
