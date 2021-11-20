package nl.techIT.techITeasy.repository;

import nl.techIT.techITeasy.model.Television;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelevisionRepository extends JpaRepository <Television, Integer> {

    Iterable<Television> findAllByName(String title);
    Iterable<Television> findAllByNameContainingIgnoreCase(String name);


}
