package nl.techIT.techITeasy.controller;

import nl.techIT.techITeasy.controller.dto.IdInputDto;
import nl.techIT.techITeasy.controller.dto.TelevisionDto;
import nl.techIT.techITeasy.controller.dto.TelevisionInputDto;
import nl.techIT.techITeasy.model.Television;
import nl.techIT.techITeasy.service.TelevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        // Response entity eruit → alleen in dev voor status codes. Nu wil je het object
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
    public TelevisionDto createTelevision(@RequestBody TelevisionInputDto dto){
        var television = televisionService.createTelevision(dto.toTelevision());
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
        var existingTV = televisionService.updateTelevision(id, television);
        return TelevisionDto.fromTelevision(existingTV);
    }

    //PUT ASSIGN REMOTE TO TV
    @PutMapping("/televisions/{id}/remote_controller")
    public Television assignRemoteControllerToTelevision(@PathVariable("id") Long id, @RequestBody IdInputDto input) {
        var television =televisionService.assignRemoteControllerToTelevision(id, input.id);
        return television;
    }

    //GET ALL TV FROM A BRAND
    @GetMapping("/televisions?brand={brand}")
    public List<TelevisionDto> getAllTelevisionsFromABrand(@RequestParam String brand) {

        var dtos = new ArrayList<TelevisionDto>();

        var televisions = televisionService.getAllTelevisionsFromABrand(brand);

        for (Television television : televisions) {
            dtos.add(TelevisionDto.fromTelevision(television));
        }

        return dtos;
    }

    @PutMapping("/televisions/{id}/ci_module")
    public void assignCIModuleToTelevision(@PathVariable("id") Long id, @PathVariable("ciModuleId") Long ciModuleId) {
        televisionService.assignCIModuleToTelevision(id, ciModuleId);
    }

//    @GetMapping("/televisions/wallBrackets/{televisionId}")
//    public Collection<WallBracket> getWallBracketsByTelevisionId(@PathVariable("televisionId") Long televisionId){
//        return televisionWallBracketService.getTelevisionWallBracketByTelevisionId(televisionId);
//    }
}
