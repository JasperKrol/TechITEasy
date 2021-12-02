package nl.techIT.techITeasy.controller;

import nl.techIT.techITeasy.model.RemoteController;
import nl.techIT.techITeasy.service.RemoteControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class RemoteControllerController {

    //Talks to service layer
    @Autowired
    private RemoteControllerService remoteControllerService;

    //Requests
    //Get all
    @GetMapping(value = "/remote_controllers")
    public ResponseEntity<Object> getAllRemoteControllers(@RequestParam(name = "brand", defaultValue = "") String brand) {
        return ResponseEntity.ok(remoteControllerService.getAllRemoteControllers());
    }

    //Get one
    @GetMapping(value = "/remote_controllers/{id}")
    public ResponseEntity<Object> getRemoteController(@PathVariable("id") Long id) {
        return ResponseEntity.ok(remoteControllerService.getRemoteController(id));
    }

    //Post
    @PostMapping(value = "/remote_controllers")
    public ResponseEntity<Object> createRemoteController(@RequestBody RemoteController remoteController) {
        long newID = remoteControllerService.createRemoteController(remoteController);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newID).toUri();

        return ResponseEntity.created(location).build();
    }

    //Update
    @PutMapping(value = "/remote_controllers/{id}")
    public ResponseEntity<Object> updateRemoteController(@PathVariable("id") Long id, @RequestBody RemoteController remoteController){
        remoteControllerService.updateRemoteController(id,remoteController);
        return ResponseEntity.noContent().build();
    }

    //Delete
    @DeleteMapping(value = "/remote_controllers/{id}")
    public ResponseEntity<Object> deleteRemoteController(@PathVariable("id") long id) {
        remoteControllerService.deleteRemoteController(id);
        return ResponseEntity.noContent().build();
    }


}
