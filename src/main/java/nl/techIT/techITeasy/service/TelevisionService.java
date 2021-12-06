package nl.techIT.techITeasy.service;

import nl.techIT.techITeasy.controller.dto.TelevisionDto;
import nl.techIT.techITeasy.exceptions.BadRequestException;
import nl.techIT.techITeasy.exceptions.RecordNotFoundException;
import nl.techIT.techITeasy.model.CIModule;
import nl.techIT.techITeasy.model.RemoteController;
import nl.techIT.techITeasy.model.Television;
import nl.techIT.techITeasy.repository.CIModuleRepository;
import nl.techIT.techITeasy.repository.RemoteControllerRepository;
import nl.techIT.techITeasy.repository.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {

    private TelevisionRepository televisionRepository;
    private RemoteControllerRepository remoteControllerRepository;
    private CIModuleRepository ciModuleRepository;

    @Autowired
    public TelevisionService(TelevisionRepository televisionRepository, RemoteControllerRepository remoteControllerRepository, CIModuleRepository ciModuleRepository) {
        this.televisionRepository = televisionRepository;
        this.remoteControllerRepository = remoteControllerRepository;
        this.ciModuleRepository = ciModuleRepository;
    }

    //Services ombouwen naar ontvangsten DTO, je ontvangt niet meer het object maar de data(DTO)

    public List<TelevisionDto> getAllTelevisions() {
        var dtos = new ArrayList<TelevisionDto>();
        var televisions = televisionRepository.findAll();

        for (Television television : televisions) {
            dtos.add(TelevisionDto.fromTelevision(television));
        }
        return dtos;
    }

    public Television getTelevision(Long id) {
        Optional<Television> television = televisionRepository.findById(id);

        if (television.isPresent()) {
            return television.get();
        } else {
            throw new RecordNotFoundException("ID not found");
        }
    }

    public Television createTelevision(Television television) {
        //argument voor als tv al bestaat → name
        String name = television.getName();
        List<Television> televisions = (List<Television>) televisionRepository.findAllByName(name);

        if (televisions.size() > 0) {
            throw new BadRequestException("TV Already exists");
        } else {
            return televisionRepository.save(television);
        }
    }

    public void deleteTelevision(Long id) {
        if (televisionRepository.existsById(id)) {
            televisionRepository.deleteById(id);
        } else {
            throw new BadRequestException("ID not found!");
        }
    }

    public Television updateTelevision(long id, Television television) {
        if (!televisionRepository.existsById(id)) {
            throw new RecordNotFoundException("Television not found");
        } else {
            Television storedTelevision = televisionRepository.findById(id).orElse(null);
            storedTelevision.setId(storedTelevision.getId());
            storedTelevision.setAmbiLight(television.getAmbiLight());
            storedTelevision.setAvailableSize(television.getAvailableSize());
            storedTelevision.setBluetooth(television.getBluetooth());
            storedTelevision.setBrand(television.getBrand());
            storedTelevision.setHdr(television.getHdr());
            storedTelevision.setId(storedTelevision.getId());
            storedTelevision.setName(television.getName());
            storedTelevision.setOriginalStock(television.getOriginalStock());
            storedTelevision.setPrice(television.getPrice());
            storedTelevision.setRefreshRate(television.getRefreshRate());
            storedTelevision.setScreenType(television.getScreenType());
            storedTelevision.setScreenQuality(television.getScreenQuality());
            storedTelevision.setSmartTv(television.getSmartTv());
            storedTelevision.setSold(television.getSold());
            storedTelevision.setType(television.getType());
            storedTelevision.setVoiceControl(television.getVoiceControl());
            storedTelevision.setWifi(television.getWifi());
            televisionRepository.save(storedTelevision);

            return storedTelevision;
        }
    }

    public Television assignRemoteControllerToTelevision(Long id, Long remotecontrollerId) {
        var optionalTelevision = televisionRepository.findById(id);
        var optionalRemoteController = remoteControllerRepository.findById(remotecontrollerId);

        if (optionalTelevision.isPresent() && optionalRemoteController.isPresent()) {
            var television = optionalTelevision.get();
            var remoteController = optionalRemoteController.get();

            television.setRemoteController(remoteController);
            televisionRepository.save(television);
            return television;
        } else {
            throw new RecordNotFoundException();
        }
    }

    public List<Television> getAllTelevisionsFromABrand(String brand) {
        return televisionRepository.findAllByBrandContainingIgnoreCase(brand);
    }

    public void assignCIModuleToTelevision(Long id, Long ciModuleId) {
        var optionalTelevision = televisionRepository.findById(id);
        var optionalCIModule = ciModuleRepository.findById(ciModuleId);

        if (optionalTelevision.isPresent() && optionalCIModule.isPresent()) {
            var television = optionalTelevision.get();
            var ciModule = optionalCIModule.get();

            television.setCiModule(ciModule);
            televisionRepository.save(television);
        } else {
            throw new RecordNotFoundException();
        }
    }

}
