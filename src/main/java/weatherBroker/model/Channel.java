package weatherBroker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Table;
import java.io.Serializable;
import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table
public class Channel implements Serializable {
    private int id;
    private Units units;
    private Wind wind;
    private Atmosphere atmosphere;
    private Astronomy astronomy;
    private Item item;

    private Results results;


    public Channel() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable=false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "channel_id")
    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "channel_id")
    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "channel_id")
    public Units getUnits() {
        return units;
    }

    public void setUnits(Units units) {
        this.units = units;
    }

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "channel_id")
    public Astronomy getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(Astronomy astronomy) {
        this.astronomy = astronomy;
    }

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "channel_id")
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "channel")
    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }
}
