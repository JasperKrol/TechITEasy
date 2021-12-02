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

    public Iterable<RemoteController> getAllRemoteControllers() {
        return remoteControllerRepository.findAll();
    }

    public Object getRemoteController(Long id) {
        Optional<RemoteController> optionalRemoteController = remoteControllerRepository.findById(id);

        if (optionalRemoteController.isPresent()){
            return optionalRemoteController.get();
        } else {
            throw new RecordNotFoundException("ID for remote not found");
        }
    }

    public Long createRemoteController(RemoteController remoteController) {
        String brand = remoteController.getBrand();
        List<RemoteController> controllers = (List<RemoteController>) remoteControllerRepository.findAllByBrand(brand);
        if (controllers.size() >0){
            throw new BadRequestException("Already in stock");
        }
        RemoteController newRemoteController = remoteControllerRepository.save(remoteController);
        return newRemoteController.getId();
    }

    public void updateRemoteController(Long id, RemoteController remoteController) {
        Optional<RemoteController> optionalRemoteController = remoteControllerRepository.findById(id);
        if (optionalRemoteController.isPresent()){
            RemoteController existingRemote = optionalRemoteController.get();
            remoteController.setId(existingRemote.getId());
            remoteControllerRepository.save(remoteController);
        } else {
            throw new RecordNotFoundException("Remote with ID not found");
        }
    }

    public void deleteRemoteController(long id) {
        if (remoteControllerRepository.existsById(id)){
            remoteControllerRepository.deleteById(id);
        }else {
            throw new BadRequestException("Remote with ID not found");
        }
    }
}
