package nl.techIT.techITeasy.repository;

import nl.techIT.techITeasy.model.Television;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelevisionRepository extends JpaRepository<Television, Integer> {


    Iterable<Television> findAllByNameContainingIgnoreCase(String name);
    Iterable<Television> findAllByName(String tvName);
//    Iterable<Television> findByLongId(Long id);
}
