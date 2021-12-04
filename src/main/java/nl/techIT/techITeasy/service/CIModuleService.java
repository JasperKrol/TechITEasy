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
    public List<CIModule> getAllCIModules() {
        return ciModuleRepository.findAll();
    }

    public CIModule getOneModule(long id) {
        Optional<CIModule> optionalCIModule = ciModuleRepository.findById(id);
        if (optionalCIModule.isPresent()) {
            return optionalCIModule.get();
        } else {
            throw new RecordNotFoundException("CI-Module with id not found");
        }
    }

    public CIModule createCiModule(CIModule ciModule) {
        String name = ciModule.getName();
        List<CIModule> ciModules = (List<CIModule>) ciModuleRepository.findAllByName(name);

        if (ciModules.size() > 0) {
            throw new BadRequestException("Module with identical name exists");
        } else {
            CIModule newCiModule = ciModuleRepository.save(ciModule);
            return newCiModule;
        }
    }

    public void deleteCiModule(Long id) {
        if (ciModuleRepository.existsById(id)) {
            ciModuleRepository.deleteById(id);
        } else {
            throw new BadRequestException("Remote with ID not found");
        }
    }

    public CIModule updateCiModule(Long id, CIModule ciModule) {
        if (!ciModuleRepository.existsById(id)) {
            throw new RecordNotFoundException("Module with ID not found");

        } else {
            CIModule existingCIModule = ciModuleRepository.findById(id).orElse(null);
            existingCIModule.setId(existingCIModule.getId());
            existingCIModule.setName(ciModule.getName());
            existingCIModule.setType(ciModule.getType());
            existingCIModule.setPrice(ciModule.getPrice());

            ciModuleRepository.save(existingCIModule);
            return existingCIModule;
        }
    }
}
