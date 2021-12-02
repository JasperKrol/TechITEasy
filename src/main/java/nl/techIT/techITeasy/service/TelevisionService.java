package nl.techIT.techITeasy.service;

import nl.techIT.techITeasy.controller.dto.TelevisionDto;
import nl.techIT.techITeasy.exceptions.BadRequestException;
import nl.techIT.techITeasy.exceptions.RecordNotFoundException;
import nl.techIT.techITeasy.model.Television;
import nl.techIT.techITeasy.repository.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {

    @Autowired
    private final TelevisionRepository televisionRepository;

    @Autowired
    public TelevisionService(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
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

    public Television addTelevision(Television television) {
        //argument voor als tv al bestaat → name
        String tvName = television.getName();
        //haal de tv's op en controleer of ze aanwezig zijn
        List<Television> televisions = (List<Television>) televisionRepository.findAllByName(tvName);
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

    public void updateTelevision(long id, Television television) {
        if (!televisionRepository.existsById(id)){
            throw new RecordNotFoundException("Television not found");
        } else {
            Television storedTelevision = televisionRepository.findById(id).orElse(null);
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
        }
    }

    // TODO: 27-11-2021 Patch update tv
//    public void partialUpdateTelevision(long id, Television television) {
//        Optional<Television> optionalTelevision = televisionRepository.findById(id);
//        // conditie maken of dat hij er wel is
//        if (optionalTelevision.isPresent()) {
//            Television storedTV = televisionRepository.findById(id).orElse(null);
//
//            //conditie maken om te kijken wat er gewijzigd is (if not empty → then
//            if (television.getName() != null && !television.getName().isEmpty()) {
//                storedTV.setName(television.getName());
//            }
//            if (television.getPrice() != null && !television.getPrice().isNaN()) {
//                storedTV.setPrice(television.getPrice());
//            }
//            //opslaan van de wijzing in het storedBook variabele
//            televisionRepository.save(storedTV);
//        } else {
//            throw new RecordNotFoundException("No Book with that id found");
//        }
//
//    }
}
