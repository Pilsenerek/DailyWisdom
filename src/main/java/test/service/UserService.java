package test.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.entity.DailyWisdom;
import test.entity.User;
import test.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getUsers(){
		return (List<User>)userRepository.findAll();
	}
	
	public List<test.dto.User> getUsersDTO() {
		List<User> users = this.getUsers();
		List<test.dto.User> usersDTO = new ArrayList<>();
		for (User user : users) {
			test.dto.User userDTO = new test.dto.User();
			userDTO.id = user.getId();
			userDTO.email = user.getEmail();
			userDTO.firstName = user.getFirstName();
			userDTO.lastName = user.getLastName();
			for (DailyWisdom cnj : user.getDailyWisdoms()) {
				test.dto.DailyWisdom cnjDTO = new test.dto.DailyWisdom();
				cnjDTO.id = cnj.getId();
				cnjDTO.joke = cnj.getJoke();
				cnjDTO.slug = cnj.getSlug();
				userDTO.dailyWisdoms.add(cnjDTO);
			}
			usersDTO.add(userDTO);

		}

		return usersDTO;
	}
	
	public test.dto.User getUserDTO(Long id) {
		User user = this.getUser(id);
		test.dto.User userDTO = new test.dto.User();
		userDTO.id = user.getId();
		userDTO.email = user.getEmail();
		userDTO.firstName = user.getFirstName();
		userDTO.lastName = user.getLastName();
		for (DailyWisdom cnj : user.getDailyWisdoms()) {
			test.dto.DailyWisdom cnjDTO = new test.dto.DailyWisdom();
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
