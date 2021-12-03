package nl.techIT.techITeasy.repository;

import nl.techIT.techITeasy.model.CIModule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CIModuleRepository extends JpaRepository<CIModule,Long> {
    Iterable<CIModule> findAllByName(String Name);
}
