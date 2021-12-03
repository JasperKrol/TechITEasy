package nl.techIT.techITeasy.service;

import nl.techIT.techITeasy.exceptions.BadRequestException;
import nl.techIT.techITeasy.exceptions.RecordNotFoundException;
import nl.techIT.techITeasy.model.CIModule;
import nl.techIT.techITeasy.repository.CIModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CIModuleService {
    @Autowired
    private CIModuleRepository ciModuleRepository;

    //Methods
    public Iterable<CIModule> getAllCIModules() {
        return ciModuleRepository.findAll();
    }

    public Object getOneModule(long id) {
        Optional<CIModule> optionalCIModule = ciModuleRepository.findById(id);
        if (optionalCIModule.isPresent()) {
            return optionalCIModule.get();
        } else {
            throw new BadRequestException("CI-Module with id not found");
        }
    }

    public long createCiModule(CIModule ciModule) {
        String name = ciModule.getName();
        List<CIModule> ciModules = (List<CIModule>) ciModuleRepository.findAllByName(name);

        if (ciModules.size() > 0) {
            throw new BadRequestException("Module with identical name exists");
        } else {
            CIModule newCiModule = ciModuleRepository.save(ciModule);
            return newCiModule.getId();
        }
    }

    public void updateCiModule(Long id, CIModule ciModule) {
        if (ciModuleRepository.existsById(id)) {
            CIModule storedCiModule = ciModuleRepository.findById(id).orElse(null);
            storedCiModule.setName(storedCiModule.getName());
            storedCiModule.setType(storedCiModule.getType());
            storedCiModule.setPrice(storedCiModule.getPrice());
            ciModuleRepository.save(storedCiModule);
        } else {
            throw new RecordNotFoundException("Module with ID not found");
        }
    }

    public void deleteCiModule(Long id) {
        if (ciModuleRepository.existsById(id)) {
            ciModuleRepository.deleteById(id);
        } else {
            throw new BadRequestException("Remote with ID not found");
        }
    }
}
