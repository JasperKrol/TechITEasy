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

    // TODO: 20-11-2021  Long - Int ID SWITCH Vragen
    public Iterable<Television> getTelevisions(String name) {
        if (name.isEmpty()) {
            return televisionRepository.findAll();
        } else {
            return televisionRepository.findAllByNameContainingIgnoreCase(name);
        }
    }

    public Television getTelevision(Long id) {
        Optional<Television> optionalTelevision = televisionRepository.findById(Math.toIntExact(id));

        if (optionalTelevision.isPresent()) {
            return optionalTelevision.get();
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
        if (televisionRepository.existsById(Math.toIntExact(id))){
            televisionRepository.deleteById(Math.toIntExact(id));
        } else {
            throw new BadRequestException("ID not found!");
        }

    }

    public void updateTelevision(long id, Television television) {
        Optional<Television> optionalTelevision = televisionRepository.findById(Math.toIntExact(id));

        if (optionalTelevision.isPresent()){
            Television existingTV = optionalTelevision.get();

            television.setId(existingTV.getId());
            televisionRepository.save(television);

        }
    }

    public void partialUpdateTelevision(long id, Television television) {
        Optional<Television> optionalTelevision = televisionRepository.findById(Math.toIntExact(id));
        // conditie maken of dat hij er wel is
        if (optionalTelevision.isPresent()) {
            Television storedTV = televisionRepository.findById(Math.toIntExact(id)).orElse(null);

            //conditie maken om te kijken wat er gewijzigd is (ifnot empty -> then
            if (television.getName() != null && !television.getName().isEmpty()) {
                storedTV.setName(television.getName());
            }
            if (television.getPrice() != null && !television.getPrice().isNaN()) {
                storedTV.setPrice(television.getPrice());
            }
            //opslaan van de wijzing in het storedbook varabele
            televisionRepository.save(storedTV);
        } else {
            throw new RecordNotFoundException("No Book with that id found");
        }

    }
}
