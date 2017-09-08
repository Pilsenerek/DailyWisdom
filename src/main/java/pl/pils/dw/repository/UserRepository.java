package pl.pils.dw.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pils.dw.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	
	public User findOneByEmail(String email);
	
}
