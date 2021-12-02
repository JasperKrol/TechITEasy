package nl.techIT.techITeasy.repository;

import nl.techIT.techITeasy.model.RemoteController;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RemoteControllerRepository extends JpaRepository<RemoteController, Long> {
    Iterable<RemoteController> findAllByBrand(String brand);
}
