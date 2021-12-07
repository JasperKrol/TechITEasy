package nl.techIT.techITeasy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "wall_brackets")
public class WallBracket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String size;
    private Boolean adjustable;
    private String name;
    private Double price;

    @OneToMany(mappedBy = "television")
    @JsonIgnore
    List<TelevisionWallBracket> televisionWallBrackets;

    //Getters and setters

    public Long getId() {
        return id;
    }

    public String getSize() {
        return size;
    }

    public Boolean getAdjustable() {
        return adjustable;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public List<TelevisionWallBracket> getTelevisionWallBrackets() {
        return televisionWallBrackets;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setAdjustable(Boolean adjustable) {
        this.adjustable = adjustable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setTelevisionWallBrackets(List<TelevisionWallBracket> televisionWallBrackets) {
        this.televisionWallBrackets = televisionWallBrackets;
    }
}
