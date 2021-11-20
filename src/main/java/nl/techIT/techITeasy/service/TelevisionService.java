package nl.techIT.techITeasy.service;

import nl.techIT.techITeasy.exceptions.BadRequestException;
import nl.techIT.techITeasy.exceptions.RecordNotFoundException;
import nl.techIT.techITeasy.model.Television;
import nl.techIT.techITeasy.repository.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Iterable<Television> getTelevisions(String name) {
        if (name.isEmpty()) {
            return televisionRepository.findAll();
        } else {
            return televisionRepository.findAllByNameContainingIgnoreCase(name);
        }
    }

    public Iterable getTelevision(Long id) {
        Optional<Television> optionalTelevision = televisionRepository.findById(Math.toIntExact(id));

        if (optionalTelevision.isPresent()) {
            return (Iterable) optionalTelevision.get();
        } else {
            throw new RecordNotFoundException("ID not found");
        }
    }

    public long addTelevision(Television television) {
        //argument voor als tv al bestaat -> name
        String tvName = television.getName();
        //haal de tv's op en controleer of ze aanwezig zijn
        List<Television> televisions = (List<Television>) televisionRepository.findAllByName(tvName);
        if (televisions.size() > 0) {
            throw new BadRequestException("TV Already exists");
        }
        Television newTV = televisionRepository.save(television);
        return newTV.getId();
    }

    public void deleteTelevision(Long id) {

    }

    public void partialUpdateTelevision(long id, Television television) {
    }
}
