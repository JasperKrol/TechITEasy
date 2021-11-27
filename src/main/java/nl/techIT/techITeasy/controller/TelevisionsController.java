package nl.techIT.techITeasy.controller;

import nl.techIT.techITeasy.controller.dto.TelevisionDto;
import nl.techIT.techITeasy.model.Television;
import nl.techIT.techITeasy.service.TelevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


//aangeven dat dit een controller is
@RestController
@RequestMapping("televisions")
public class TelevisionsController {

    //Maak een link met de repository laag
    @Autowired
    private TelevisionService televisionService;

    //CRUD Requests
    //PathVaribale? Dan krijg je in de param @pathvariable
    // wijzig je iets of geef je iets door @Request body - put/post - heeft beide in parameters functie

    //Get
    @GetMapping(value = "/televisions")
    public List<TelevisionDto> getAllTelevisions(@RequestParam(name = "title", defaultValue = "") String title) {
        // Response entity eruit -> alleen in dev voor status codes. Nu wil je het object
        return televisionService.getAllTelevisions();
    }

    @GetMapping(value = "/televisions/{id}")
    public ResponseEntity<Object> getTelevision(@PathVariable("id") Long id) {
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
        televisionService.deleteTelevision(id);
        return ResponseEntity.noContent().build();
    }

    //Put
    @PutMapping(value = "/televisions/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable("id") long id, @RequestBody Television television) {
        televisionService.updateTelevision(id, television);
        return ResponseEntity.noContent().build();
    }

    //Patch
    @PatchMapping(value = "/televisions/{id}")
    public ResponseEntity<Object> partialUpdateTelevision(@PathVariable("id") long id, @RequestBody Television television) {
        televisionService.partialUpdateTelevision(id, television);

        return ResponseEntity.noContent().build();
    }
}
