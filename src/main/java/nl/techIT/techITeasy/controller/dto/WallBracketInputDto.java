package nl.techIT.techITeasy.controller.dto;

import nl.techIT.techITeasy.model.WallBracket;

public class WallBracketInputDto {
    public Long id;
    public String size;
    public Boolean adjustable;
    public String name;
    public Double price;

    public WallBracket toWallBracket(){
        var wallBracket = new WallBracket();

        wallBracket.setId(id);
        wallBracket.setSize(size);
        wallBracket.setAdjustable(adjustable);
        wallBracket.setName(name);
        wallBracket.setPrice(price);

        return wallBracket;
    }
}
