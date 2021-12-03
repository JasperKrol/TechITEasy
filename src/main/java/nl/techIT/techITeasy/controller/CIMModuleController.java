package nl.techIT.techITeasy.controller;

import nl.techIT.techITeasy.model.CIModule;
import nl.techIT.techITeasy.model.RemoteController;
import nl.techIT.techITeasy.service.CIModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class CIMModuleController {

    @Autowired
    private CIModuleService ciModuleService;

    //Requests
    //Get all
    @GetMapping(value = "/ci_modules")
   public ResponseEntity<Object> getAllCIModules(){
        return ResponseEntity.ok(ciModuleService.getAllCIModules());
    }

    //Get one
    @GetMapping(value = "/ci_modules/{id}")
    public ResponseEntity<Object> getOneModule(@PathVariable("id") long id){
        return ResponseEntity.ok(ciModuleService.getOneModule(id));
    }


    //Post
    @PostMapping(value = "/ci_modules")
    public ResponseEntity<Object> createCiModule(@RequestBody CIModule ciModule) {
        long newID = ciModuleService.createCiModule(ciModule);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newID).toUri();

        return ResponseEntity.created(location).build();
    }

    //Update
    @PutMapping(value = "/ci_modules/{id}")
    public ResponseEntity<Object> updateCiModule(@PathVariable("id") Long id, @RequestBody CIModule ciModule){
        ciModuleService.updateCiModule(id, ciModule);
        return ResponseEntity.noContent().build();
    }


    //Delete
    @DeleteMapping(value = "/ci_modules/{id}")
    public ResponseEntity<Object> deleteCiModule(@PathVariable("id") Long id){
        ciModuleService.deleteCiModule(id);
        return ResponseEntity.noContent().build();
    }

}
