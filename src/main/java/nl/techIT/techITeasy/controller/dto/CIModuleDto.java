package nl.techIT.techITeasy.controller.dto;

import nl.techIT.techITeasy.model.CIModule;

public class CIModuleDto {

    // TODO: 3-12-2021 hier plaats je de annotaties voor sql
    public Long id;
    public String name;
    public String type;
    public Double price;

    //Je wilt de attributen uit het model naar je dto vormen en halen, dus get
    public static CIModuleDto fromCIModule(CIModule ciModule){
        var dto = new CIModuleDto();
        dto.name = ciModule.getName();
        dto.type = ciModule.getType();
        dto.price = ciModule.getPrice();
        return dto;
    }
}
