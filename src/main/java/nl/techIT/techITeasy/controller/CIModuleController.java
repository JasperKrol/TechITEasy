package nl.techIT.techITeasy.controller;

import nl.techIT.techITeasy.controller.dto.CIModuleDto;
import nl.techIT.techITeasy.controller.dto.CIModuleInputDto;
import nl.techIT.techITeasy.model.CIModule;
import nl.techIT.techITeasy.service.CIModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
public class CIModuleController {

    @Autowired
    private CIModuleService ciModuleService;

    //Requests
    //Get all
    @GetMapping(value = "/ci_modules")
    public List<CIModuleDto> getAllCIModules() {
        // haal alle dto att op
        var dtos = new ArrayList<CIModuleDto>();
        var ciModules = ciModuleService.getAllCIModules();

        //Haal alle cimodules
        for (CIModule cimodule : ciModules) {
            //stop de dto info fromcimodule uit object cimodule
            dtos.add(CIModuleDto.fromCIModule(cimodule));
        }
        return dtos;
    }

    //Get one
    @GetMapping(value = "/ci_modules/{id}")
    public CIModuleDto getOneModule(@PathVariable("id") long id) {
        var ciModule = ciModuleService.getOneModule(id);
        return CIModuleDto.fromCIModule(ciModule);
    }

    //Post
    @PostMapping(value = "/ci_modules")
    public CIModuleDto createCiModule(@RequestBody CIModuleInputDto dto) {
        var ciModule = ciModuleService.createCiModule(dto.toCIModule());
        return CIModuleDto.fromCIModule(ciModule);
    }

    //Delete
    @DeleteMapping(value = "/ci_modules/{id}")
    public void deleteCiModule(@PathVariable("id") Long id) {
        ciModuleService.deleteCiModule(id);
    }

    //Update
    @PutMapping(value = "/ci_modules/{id}")
    public CIModuleDto updateCiModule(@PathVariable("id") Long id, @RequestBody CIModule ciModule) {
        var existingCIModule = ciModuleService.updateCiModule(id, ciModule);
        return CIModuleDto.fromCIModule(existingCIModule);
    }
}
