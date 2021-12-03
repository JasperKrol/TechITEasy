package nl.techIT.techITeasy.controller.dto;

import nl.techIT.techITeasy.model.RemoteController;

public class RemoteControllerInputDto {

    public Long id;
    public String compatibleWith;
    public String batteryType;
    public String brand;
    public Double price;
    public Integer originalStock;
    public Integer sold;

    public RemoteController toRemoteController(){
        var remoteController = new RemoteController();
        remoteController.setId(id);
        remoteController.setCompatibleWith(compatibleWith);
        remoteController.setBatteryType(batteryType);
        remoteController.setBrand(brand);
        remoteController.setPrice(price);
        remoteController.setOriginalStock(originalStock);
        remoteController.setSold(sold);
        return remoteController;
    }
}
