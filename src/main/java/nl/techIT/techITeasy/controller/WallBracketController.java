package nl.techIT.techITeasy.controller;

import nl.techIT.techITeasy.controller.dto.WallBracketDto;
import nl.techIT.techITeasy.controller.dto.WallBracketInputDto;
import nl.techIT.techITeasy.model.Television;
import nl.techIT.techITeasy.model.WallBracket;
import nl.techIT.techITeasy.service.TelevisionWallBracketService;
import nl.techIT.techITeasy.service.WallBracketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class WallBracketController {

    private final WallBracketService wallBracketService;
    private final TelevisionWallBracketService televisionWallBracketService;

    @Autowired
    public WallBracketController(WallBracketService wallBracketService, TelevisionWallBracketService televisionWallBracketService) {
        this.wallBracketService = wallBracketService;
        this.televisionWallBracketService = televisionWallBracketService;
    }

    //Requests
    //Get
    // all
    @GetMapping(value = "/wall_brackets")
    public List<WallBracketDto> getAllWallBrackets() {
        var dtos = new ArrayList<WallBracketDto>();
        var wallBracket = wallBracketService.getAllWallBrackets();

        for (WallBracket wallbracket : wallBracket) {
            dtos.add(WallBracketDto.fromWallBracket(wallbracket));
        }
        return dtos;
    }

    // one
    @GetMapping(value = "/wall_brackets/{id}")
    public WallBracketDto getOneWallBracket(@PathVariable("id") Long id) {
        var wallBracket = wallBracketService.getOneWallBracket(id);
        return WallBracketDto.fromWallBracket(wallBracket);
    }

    //Post
    @PostMapping(value = "/wall_brackets")
    public WallBracketDto addWallBracket(@RequestBody WallBracketInputDto dto) {
        var wallBracket = wallBracketService.addWallBracket(dto.toWallBracket());
        return WallBracketDto.fromWallBracket(wallBracket);
    }

    //Delete
    @DeleteMapping(value = "/wall_brackets/{id}")
    public void deleteWallBracket(@PathVariable("id") Long id) {
        wallBracketService.deleteWallBracket(id);
    }

    //Update
    @PutMapping(value = "/wall_brackets/{id}")
    public WallBracketDto updateWallBracket(@PathVariable("id") Long id, @RequestBody WallBracket wallBracket) {
        var existingWallBracket = wallBracketService.updateWallBracket(id, wallBracket);
        return WallBracketDto.fromWallBracket(existingWallBracket);
    }

    @GetMapping("/wallbrackets/televisions/{televisionId}")
    public Collection<Television> getTelevisionsByWallBracketId(@PathVariable("wallBracketId") Long wallBracketId) {
        return televisionWallBracketService.getTelevisionWallBracketsByWallBracketId(wallBracketId);
    }

}
