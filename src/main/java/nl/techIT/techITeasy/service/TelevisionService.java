package nl.techIT.techITeasy.service;

import nl.techIT.techITeasy.exceptions.RecordNotFoundException;
import nl.techIT.techITeasy.model.Television;
import nl.techIT.techITeasy.repository.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TelevisionService {

    @Autowired
    private final TelevisionRepository televisionRepository;

    @Autowired
    public TelevisionService(TelevisionRepository televisionRepository){
        this.televisionRepository = televisionRepository;
    }

    public Iterable<Television> getTelevisions(String title) {
        if (title.isEmpty()){
            return televisionRepository.findAll();
        } else {
            return televisionRepository.findAllByTitleContainingIgnoreCase(title);
        }
    }

    public Television getTelevision(int id) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);

        if (optionalTelevision.isPresent()){
            return optionalTelevision.get();
        } else {
            throw new RecordNotFoundException("ID does not exist");
        }
    }

    public Long addTelevision(Television television){
        Television newTV = televisionRepository.save(television);
        return newTV.getId();
    }


}
