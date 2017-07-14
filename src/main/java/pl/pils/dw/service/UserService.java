package pl.pils.dw.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pils.dw.entity.DailyWisdom;
import pl.pils.dw.entity.User;
import pl.pils.dw.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getUsers(){
		return (List<User>)userRepository.findAll();
	}
	
	public List<pl.pils.dw.dto.User> getUsersDTO() {
		List<User> users = this.getUsers();
		List<pl.pils.dw.dto.User> usersDTO = new ArrayList<>();
		for (User user : users) {
			pl.pils.dw.dto.User userDTO = new pl.pils.dw.dto.User();
			userDTO.id = user.getId();
			userDTO.email = user.getEmail();
			userDTO.firstName = user.getFirstName();
			userDTO.lastName = user.getLastName();
			for (DailyWisdom cnj : user.getDailyWisdoms()) {
				pl.pils.dw.dto.DailyWisdom cnjDTO = new pl.pils.dw.dto.DailyWisdom();
				cnjDTO.id = cnj.getId();
				cnjDTO.joke = cnj.getJoke();
				cnjDTO.slug = cnj.getSlug();
				userDTO.dailyWisdoms.add(cnjDTO);
			}
			usersDTO.add(userDTO);

		}

		return usersDTO;
	}
	
	public pl.pils.dw.dto.User getUserDTO(Long id) {
		User user = this.getUser(id);
		pl.pils.dw.dto.User userDTO = new pl.pils.dw.dto.User();
		userDTO.id = user.getId();
		userDTO.email = user.getEmail();
		userDTO.firstName = user.getFirstName();
		userDTO.lastName = user.getLastName();
		for (DailyWisdom cnj : user.getDailyWisdoms()) {
			pl.pils.dw.dto.DailyWisdom cnjDTO = new pl.pils.dw.dto.DailyWisdom();
			cnjDTO.id = cnj.getId();
			cnjDTO.joke = cnj.getJoke();
			cnjDTO.slug = cnj.getSlug();
			userDTO.dailyWisdoms.add(cnjDTO);
		}
		
		return userDTO;
	}
	
	public User getUser(Long id){
		
		return this.userRepository.findOne(id);
	}
	
	public void addUser(User user){
		
		this.userRepository.save(user);
	}

	public void updateUser(Long id, User user) {
		
		this.userRepository.save(user);
		
	}

	public void deleteUser(Long id) {
		
		this.userRepository.delete(id);
	}
	

}
