package nl.techIT.techITeasy.controller;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/*
onder in de pagina is de gemakkelijke crud te vinden uit de les

 */

//aangeven dat dit een controller is
@RestController
public class TelevisionsController {

    //CRUD Requests
    //PathVaribale? Dan krijg je in de param @pathvariable
    // wijzig je iets of geef je iets door @Request body - put/post - heeft beide in parameters functie

    //Post
    @PostMapping(value = "/televisions")
    public ResponseEntity<Object> addTelevision(@RequestBody String television) {

        //nieuwe variable naam opvoeren
        // in de buildAndExpand de nieuwe id/naam invoeren
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(newId).toUri();
        //televisions.add(television)
        return ResponseEntity.created(location).build();
    }

    //Get
    @GetMapping(value = "/televisions")
    public ResponseEntity<Object> getAllTelevisions(){
        // in de ok()komt de body te staan
        return ResponseEntity.ok();
    }
    @GetMapping(value = "/televisions/{id}")
    public ResponseEntity<Object> getTelevision(@PathVariable("id") int id){
        // in de ok()komt de body te staan
        return ResponseEntity.ok(.get(id));
    }

    //Put
    @PutMapping(value = "/televisions/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable("id") int id, @RequestBody ... ....) {
        //televisions.set(id,television);
        return ResponseEntity.noContent().build();
    }

    //Delete
    @DeleteMapping(value = "/televisions/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable("id") int id){
        //televisions.remove(id)
        return ResponseEntity.noContent().build();
    }
}
