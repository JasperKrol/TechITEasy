package nl.techIT.techITeasy.service;

import nl.techIT.techITeasy.exceptions.RecordNotFoundException;
import nl.techIT.techITeasy.model.Television;
import nl.techIT.techITeasy.model.TelevisionWallBracket;
import nl.techIT.techITeasy.model.TelevisionWallBracketKey;
import nl.techIT.techITeasy.model.WallBracket;
import nl.techIT.techITeasy.repository.TelevisionRepository;
import nl.techIT.techITeasy.repository.TelevisionWallBracketRepository;
import nl.techIT.techITeasy.repository.WallBracketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class TelevisionWallBracketService {
    private final TelevisionRepository televisionRepository;
    private final WallBracketRepository wallBracketRepository;
    private final TelevisionWallBracketRepository televisionWallBracketRepository;

    @Autowired
    public TelevisionWallBracketService(TelevisionRepository televisionRepository,
                                        WallBracketRepository wallBracketRepository,
                                        TelevisionWallBracketRepository televisionWallBracketRepository){
        this.televisionRepository = televisionRepository;
        this.wallBracketRepository = wallBracketRepository;
        this.televisionWallBracketRepository = televisionWallBracketRepository;
    }

    public Collection<Television> getTelevisionWallBracketsByWallBracketId(Long wallBracketId) {
        Collection<Television> televisions = new HashSet<>();
        Collection<TelevisionWallBracket> televisionWallBrackets = televisionWallBracketRepository.findAllByWallBracketId(wallBracketId);
        for (TelevisionWallBracket televisionWallbracket : televisionWallBrackets) {
            televisions.add(televisionWallbracket.getTelevision());
        }
        return televisions;
    }

    public Collection<WallBracket> getTelevisionWallBracketByTelevisionId(Long televisionId) {
        Collection<WallBracket> wallBrackets = new HashSet<>();
        Collection<TelevisionWallBracket> televisionWallbrackets = televisionWallBracketRepository.findAllByTelevisionId(televisionId);
        for (TelevisionWallBracket televisionWallbracket : televisionWallbrackets) {
            wallBrackets.add(televisionWallbracket.getWallBracket());
        }
        return wallBrackets;
    }


    public TelevisionWallBracketKey addTelevisionWallBracket(Long televisionId, Long wallBracketId) {
        var televisionWallBracket = new TelevisionWallBracket();
        if (!televisionRepository.existsById(televisionId)) {throw new RecordNotFoundException();}
        Television television = televisionRepository.findById(televisionId).orElse(null);
        if (!wallBracketRepository.existsById(wallBracketId)) {throw new RecordNotFoundException();}
        WallBracket wallBracket = wallBracketRepository.findById(wallBracketId).orElse(null);
        televisionWallBracket.setTelevision(television);
        televisionWallBracket.setWallBracket(wallBracket);
        TelevisionWallBracketKey id = new TelevisionWallBracketKey(televisionId, wallBracketId);
        televisionWallBracket.setId(id);
        televisionWallBracketRepository.save(televisionWallBracket);
        return id;
    }
}
