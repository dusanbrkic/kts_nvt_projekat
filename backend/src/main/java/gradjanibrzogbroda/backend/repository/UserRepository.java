package gradjanibrzogbroda.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gradjanibrzogbroda.backend.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findOneByUsername(String username);

}
