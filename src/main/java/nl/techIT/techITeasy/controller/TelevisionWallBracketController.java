package nl.techIT.techITeasy.controller;


import nl.techIT.techITeasy.service.TelevisionWallBracketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class TelevisionWallBracketController {

    @Autowired
    private TelevisionWallBracketService televisionWallBracketService;

   @Autowired
    public TelevisionWallBracketController(TelevisionWallBracketService televisionWallBracketService) {
        this.televisionWallBracketService = televisionWallBracketService;
    }

    @PostMapping("/televisions/{televisionId}/{wallBracketId}")
    public void addTelevisionWallBracket(@PathVariable("televisionId") Long televisionId, @PathVariable("wallBracketId") Long wallbracketId) {
        televisionWallBracketService.addTelevisionWallBracket(televisionId, wallbracketId);
    }
}
