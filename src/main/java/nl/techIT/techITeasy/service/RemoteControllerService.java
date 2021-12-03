package nl.techIT.techITeasy.service;

import nl.techIT.techITeasy.exceptions.BadRequestException;
import nl.techIT.techITeasy.exceptions.RecordNotFoundException;
import nl.techIT.techITeasy.model.RemoteController;
import nl.techIT.techITeasy.repository.RemoteControllerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RemoteControllerService {
    //Talks to repository layer
    @Autowired
    private RemoteControllerRepository remoteControllerRepository;

    public List<RemoteController> getAllRemoteControllers() {
        return remoteControllerRepository.findAll();
    }

    public RemoteController getRemoteController(Long id) {
        Optional<RemoteController> optionalRemoteController = remoteControllerRepository.findById(id);

        if (optionalRemoteController.isPresent()) {
            return optionalRemoteController.get();
        } else {
            throw new RecordNotFoundException("ID for remote not found");
        }
    }

    public RemoteController createRemoteController(RemoteController remoteController) {
        String brand = remoteController.getBrand();
        List<RemoteController> controllers = (List<RemoteController>) remoteControllerRepository.findAllByBrand(brand);
        if (controllers.size() > 0) {
            throw new BadRequestException("Already in stock");
        } else {
            RemoteController newRemoteController = remoteControllerRepository.save(remoteController);
            return newRemoteController;
        }
    }

    public void updateRemoteController(Long id, RemoteController remoteController) {
        if (!remoteControllerRepository.existsById(id)) {
            RemoteController existingRemote = remoteControllerRepository.findById(id).orElse(null);
            existingRemote.setCompatibleWith(remoteController.getCompatibleWith());
            existingRemote.setBatteryType(remoteController.getBatteryType());
            existingRemote.setBrand(remoteController.getBrand());
            existingRemote.setName(remoteController.getName());
            existingRemote.setPrice(remoteController.getPrice());
            existingRemote.setOriginalStock(remoteController.getOriginalStock());
            existingRemote.setSold(remoteController.getSold());
            remoteControllerRepository.save(existingRemote);
        } else {
            throw new RecordNotFoundException("Remote with ID not found");
        }
    }

    public void deleteRemoteController(long id) {
        if (remoteControllerRepository.existsById(id)) {
            remoteControllerRepository.deleteById(id);
        } else {
            throw new BadRequestException("Remote with ID not found");
        }
    }
}
