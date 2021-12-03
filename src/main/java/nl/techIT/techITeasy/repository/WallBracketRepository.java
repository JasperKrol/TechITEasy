package nl.techIT.techITeasy.repository;

import nl.techIT.techITeasy.model.WallBracket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WallBracketRepository extends JpaRepository <WallBracket,Long> {
    Iterable<WallBracket> findAllByName(String name);
}
