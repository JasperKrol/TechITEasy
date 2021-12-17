package nl.techIT.techITeasy.repository;

import nl.techIT.techITeasy.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
