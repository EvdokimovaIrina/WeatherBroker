package weatherBroker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Table;
import java.io.Serializable;
import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table
public class Channel implements Serializable {
    @JsonIgnore
    private int id;

    private Units units;

    private Wind wind;

    private Atmosphere atmosphere;

    private Astronomy astronomy;

    private Item item;

    public Channel() {
    }

    @Id
 //   @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable=false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="wind_id")
    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="atmosphere_id")
    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="units_id")
    public Units getUnits() {
        return units;
    }

    public void setUnits(Units units) {
        this.units = units;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="astronomy_id")
    public Astronomy getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(Astronomy astronomy) {
        this.astronomy = astronomy;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="item_id")
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}
