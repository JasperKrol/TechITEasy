package nl.techIT.techITeasy.controller.dto;

import nl.techIT.techITeasy.model.RemoteController;

public class RemoteControllerInputDto {
    public Long id;
    public String compatibleWith;
    public String batteryType;
    public String name;
    public String brand;
    public Double price;
    public Integer originalStock;

    public RemoteController toRemoteController() {

        var remoteController = new RemoteController();

        remoteController.setId(id);
        remoteController.setCompatibleWith(compatibleWith);
        remoteController.setBatteryType(batteryType);
        remoteController.setName(name);
        remoteController.setBrand(brand);
        remoteController.setPrice(price);
        remoteController.setOriginalStock(originalStock);

        return remoteController;
    }
}

