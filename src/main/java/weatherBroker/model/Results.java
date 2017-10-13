package weatherBroker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Table;
import java.io.Serializable;
import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "Results")
public class Results implements Serializable {
    private int id;
    private Channel channel;

    private QueryWeather weather;

    public Results() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable=false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "results_id")
    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public QueryWeather getWeather() {
        return weather;
    }

    public void setWeather(QueryWeather weather) {
        this.weather = weather;
    }
}
