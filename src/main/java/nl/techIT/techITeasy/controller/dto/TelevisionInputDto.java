package nl.techIT.techITeasy.controller.dto;

import com.sun.istack.NotNull;
import nl.techIT.techITeasy.model.Television;

public class TelevisionInputDto {

    //Attributes
    public Long id;
    public String type;
    public String brand;
    public String name;
    public Double price;
    public Double availableSize;
    public Double refreshRate;
    public String screenType;
    public String screenQuality;
    public Boolean smartTv;
    public Boolean wifi;
    public Boolean voiceControl;
    public Boolean hdr;
    public Boolean bluetooth;
    public Boolean ambiLight;
    public Integer originalStock;
    public Integer sold;

    //Methods
    // hier instantieer je het object en zet je de waardes
    public Television toTelevision() {
        var television = new Television();
        television.setId(id);
        television.setType(type);
        television.setBrand(brand);
        television.setName(name);
        television.setPrice(price);
        television.setAvailableSize(availableSize);
        television.setRefreshRate(refreshRate);
        television.setScreenType(screenType);
        television.setScreenQuality(screenQuality);
        television.setSmartTv(smartTv);
        television.setWifi(wifi);
        television.setVoiceControl(voiceControl);
        television.setHdr(hdr);
        television.setBluetooth(bluetooth);
        television.setAmbiLight(ambiLight);
        television.setOriginalStock(originalStock);
        television.setSold(sold);
        return television;
    }
}
