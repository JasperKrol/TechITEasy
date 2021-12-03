package nl.techIT.techITeasy.controller.dto;

import nl.techIT.techITeasy.model.CIModule;

public class CIModuleInputDto {
    public Long id;
    public String name;
    public String type;
    public Double price;

    //Je wilt de CI module model aanmaken en naar je dto vormen, dus instantieer en set
    public CIModule toCIModule(){
        var ciModule = new CIModule();
        ciModule.setId(id);
        ciModule.setName(name);
        ciModule.setType(type);
        ciModule.setPrice(price);

        return ciModule;
    }
}
