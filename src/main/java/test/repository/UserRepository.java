package test.repository;

import org.springframework.data.repository.CrudRepository;

import test.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	
	
	
}
