package nl.techIT.techITeasy.model;

import javax.persistence.*;

@Entity
public class TelevisionWallBracket {

    @EmbeddedId
    private TelevisionWallBracketKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("televisionId")
    @JoinColumn(name = "television_id")
    private Television television;

    @ManyToOne
    @MapsId("wallBracketId")
    @JoinColumn(name = "wall_bracket_id")
    private WallBracket wallBracket;



    public Television getTelevision() {
        return television;
    }

    public WallBracket getWallBracket() {
        return wallBracket;
    }


    public void setTelevision(Television television) {
        this.television = television;
    }

    public void setWallBracket(WallBracket wallBracket) {
        this.wallBracket = wallBracket;
    }

    public TelevisionWallBracketKey getId() {
        return id;
    }

    public void setId(TelevisionWallBracketKey id) {
        this.id = id;
    }
}