package nl.techIT.techITeasy.controller;

import nl.techIT.techITeasy.model.WallBracket;
import nl.techIT.techITeasy.service.WallBracketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class WallBracketController {

    @Autowired
    private WallBracketService wallBracketService;

    //Requests
    //Get
    // all
    @GetMapping(value = "/wall_brackets")
    public ResponseEntity<Object> getAllWallBrackets() {
        return ResponseEntity.ok(wallBracketService.getAllWallBrackets());
    }
    // one
    @GetMapping(value = "/wall_brackets/{id}")
    public ResponseEntity<Object> getOneWallBracket(@PathVariable("id") Long id) {
        return ResponseEntity.ok(wallBracketService.getOneWallBracket(id));
    }

    //Post
    @PostMapping(value = "/wall_brackets")
    public ResponseEntity<Object> addWallBracket(@RequestBody WallBracket wallBracket) {
        long newID = wallBracketService.addWallBracket(wallBracket);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newID).toUri();

        return ResponseEntity.created(location).build();
    }

    //Delete
    @DeleteMapping(value = "/wall_brackets/{id}")
    public ResponseEntity<Object> deleteWallBracket(@PathVariable("id") Long id) {
        wallBracketService.deleteWallBracket(id);
        return ResponseEntity.noContent().build();
    }

    //Update
    @PutMapping(value = "/wall_brackets/{id}")
    public ResponseEntity<Object> updateWallBracket(@PathVariable("id") Long id, @RequestBody WallBracket wallBracket){
        wallBracketService.updateWallBracket(id, wallBracket);
        return ResponseEntity.ok().build();
    }

}
