package nl.techIT.techITeasy.service;

import nl.techIT.techITeasy.exceptions.BadRequestException;
import nl.techIT.techITeasy.exceptions.RecordNotFoundException;
import nl.techIT.techITeasy.model.WallBracket;
import nl.techIT.techITeasy.repository.WallBracketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WallBracketService {

    @Autowired
    private WallBracketRepository wallBracketRepository;


    public long addWallBracket(WallBracket wallBracket) {
        String name = wallBracket.getName();

        List<WallBracket> wallBrackets = (List<WallBracket>) wallBracketRepository.findAllByName(name);
        if (wallBrackets.size() > 0) {
            throw new BadRequestException("Wall bracket with name already exists");
        }

        WallBracket newWallBracket = wallBracketRepository.save(wallBracket);
        return newWallBracket.getId();
    }

    public Iterable<WallBracket> getAllWallBrackets() {
        return wallBracketRepository.findAll();
    }

    public WallBracket getOneWallBracket(Long id) {
        Optional existingWallBracket = wallBracketRepository.findById(id);
        if (existingWallBracket.isPresent()) {
            return wallBracketRepository.getById(id);
        } else {
            throw new RecordNotFoundException("ID for wall bracket not found");
        }
    }

    public void deleteWallBracket(Long id) {
        if (wallBracketRepository.existsById(id)) {
            wallBracketRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("ID for wall bracket not found");
        }
    }

    public void updateWallBracket(Long id, WallBracket wallBracket) {
        if (!wallBracketRepository.existsById(id)) {
            throw new RecordNotFoundException("ID for wall bracket not found");
        } else {
            WallBracket storedWallBracket = wallBracketRepository.findById(id).orElse(null);
            storedWallBracket.setName(wallBracket.getName());
            storedWallBracket.setPrice(wallBracket.getPrice());
            storedWallBracket.setAdjustable(wallBracket.getAdjustable());
            storedWallBracket.setSize(wallBracket.getSize());

            wallBracketRepository.save(storedWallBracket);
        }
    }
}
