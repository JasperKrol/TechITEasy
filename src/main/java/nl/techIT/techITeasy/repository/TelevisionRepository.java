package nl.techIT.techITeasy.repository;

import nl.techIT.techITeasy.model.Television;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelevisionRepository extends JpaRepository<Television, Long> {


    Iterable<Television> findAllByNameContainingIgnoreCase(String name);
    Iterable<Television> findAllByName(String tvName);
    List<Television> findAllByBrandContainingIgnoreCase(String brand);

    //    Iterable<Television> findByLongId(Long id);
}
