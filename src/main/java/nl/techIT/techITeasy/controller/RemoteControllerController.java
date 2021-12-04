package nl.techIT.techITeasy.controller;

import nl.techIT.techITeasy.controller.dto.RemoteControllerDto;
import nl.techIT.techITeasy.controller.dto.RemoteControllerInputDto;
import nl.techIT.techITeasy.model.RemoteController;
import nl.techIT.techITeasy.service.RemoteControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RemoteControllerController {

    //Talks to service layer
    @Autowired
    private RemoteControllerService remoteControllerService;

    //Requests
    //Get all
    @GetMapping(value = "/remote_controllers")
    public List<RemoteControllerDto> getAllRemoteControllers(@RequestParam(name = "brand", defaultValue = "") String brand) {
        var dtos = new ArrayList<RemoteControllerDto>();
        var remoteControllers = remoteControllerService.getAllRemoteControllers();

        for (RemoteController remoteController : remoteControllers) {
            dtos.add(RemoteControllerDto.fromRemoteController(remoteController));
        }
        return dtos;
    }

    //Get one
    @GetMapping(value = "/remote_controllers/{id}")
    public RemoteControllerDto getRemoteController(@PathVariable("id") Long id) {
        var remoteController = remoteControllerService.getRemoteController(id);
        return RemoteControllerDto.fromRemoteController(remoteController);
    }

    //Post
    @PostMapping(value = "/remote_controllers")
    // Je stuurt de DTO, maar in de body komt de inputDTO dto voor de functies om de DTO te maken
    // service.createcontroller(dto.toRemote) om hem te vullen en aan te kunnen spreken
    public RemoteControllerDto createRemoteController(@RequestBody RemoteControllerInputDto dto) {
        var remoteController = remoteControllerService.createRemoteController(dto.toRemoteController());
        return RemoteControllerDto.fromRemoteController(remoteController);
    }

    //Delete
    @DeleteMapping(value = "/remote_controllers/{id}")
    public void deleteRemoteController(@PathVariable("id") long id) {
        remoteControllerService.deleteRemoteController(id);
    }

    //Update
    @PutMapping(value = "/remote_controllers/{id}")
    public RemoteControllerDto updateRemoteController(@PathVariable("id") Long id, @RequestBody RemoteController remoteController) {
        var existingRemoteController =  remoteControllerService.updateRemoteController(id, remoteController);
        return RemoteControllerDto.fromRemoteController(existingRemoteController);
    }
}
