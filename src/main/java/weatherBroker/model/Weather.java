package weatherBroker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "weather")
@JsonRootName(value = "wind")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather implements Serializable {

    private String id;
    private String chill;
    private String direction;
    private String speed;

    public Weather() {
    }

    public String getChill() {
        return chill;
    }

    public void setChill(String chill) {
        this.chill = chill;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
