package nl.techIT.techITeasy.controller;

import nl.techIT.techITeasy.model.Television;
import nl.techIT.techITeasy.repository.TelevisionRepository;
import nl.techIT.techITeasy.service.TelevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


//aangeven dat dit een controller is
@RestController
public class TelevisionsController {

    //Maak een link met de repository laag
    @Autowired
    private TelevisionService televisionService;

    //CRUD Requests
    //PathVaribale? Dan krijg je in de param @pathvariable
    // wijzig je iets of geef je iets door @Request body - put/post - heeft beide in parameters functie

    //Get
    @GetMapping(value = "/televisions")
    public ResponseEntity<Object> getAllTelevisions(@RequestParam(name = "title", defaultValue = "")String title) {
        // in de ok()komt de body te staan
        return ResponseEntity.ok(televisionService.getTelevisions(title));
    }

    @GetMapping(value = "/televisions/{id}")
    public ResponseEntity<Object> getTelevision(@PathVariable("id") int id) {
        // in de ok()komt de body te staan
        return ResponseEntity.ok(televisionService.getTelevision(id));
    }

    //Post
    @PostMapping(value = "/televisions")
    public ResponseEntity<Object> addTelevision(@RequestBody Television television) {

        long newId = televisionService.addTelevision(television);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();
    }

    //Delete
    @DeleteMapping(value = "/televisions/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable("id") Long id) {
        //televisions.remove(id)
        return ResponseEntity.noContent().build();
    }

    //Put
    @PutMapping(value = "/televisions/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable("id") int id, @RequestBody String name) {
        //televisions.set(id,television);
        return ResponseEntity.noContent().build();
    }
}
