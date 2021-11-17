package nl.techIT.techITeasy;

import nl.techIT.techITeasy.repository.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelevisionService {

    private final TelevisionRepository televisionRepository;

    @Autowired
    public TelevisionService(TelevisionRepository televisionRepository){
        this.televisionRepository = televisionRepository;
    }
}
