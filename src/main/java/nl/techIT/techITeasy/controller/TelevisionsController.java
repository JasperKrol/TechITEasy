package nl.techIT.techITeasy.controller;

import nl.techIT.techITeasy.controller.dto.TelevisionDto;
import nl.techIT.techITeasy.controller.dto.TelevisionInputDto;
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
    public TelevisionDto getTelevision(@PathVariable("id") Long id) {
        //je krijgt geen lijst maar object
        var television = televisionService.getTelevision(id);
        //return de DTO
        return TelevisionDto.fromTelevision(television);
    }

    //Post
    //Je wilt alles via de DTO laten lopen, parameters DTO -> input
    @PostMapping(value = "/televisions")
    public TelevisionDto addTelevision(@RequestBody TelevisionInputDto dto) {
        var television = televisionService.addTelevision(dto.toTelevision());

        return TelevisionDto.fromTelevision(television);
    }

    //Delete
    @DeleteMapping(value = "/televisions/{id}")
    public void deleteTelevision(@PathVariable("id") Long id) {
        televisionService.deleteTelevision(id);
    }

    //Put
    @PutMapping(value = "/televisions/{id}")
    public TelevisionDto updateTelevision(@PathVariable("id") long id, @RequestBody Television television) {
        televisionService.updateTelevision(id, television);
        return TelevisionDto.fromTelevision(television);
    }

    //Patch
    @PatchMapping(value = "/televisions/{id}")
    public TelevisionDto partialUpdateTelevision(@PathVariable("id") long id, @RequestBody Television television) {
        televisionService.partialUpdateTelevision(id, television);

        return TelevisionDto.fromTelevision(television);
    }
}
