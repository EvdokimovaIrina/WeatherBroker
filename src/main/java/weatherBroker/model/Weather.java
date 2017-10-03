package weatherBroker.model;

import javax.persistence.*;

@Entity
@Table(name = "weather")
public class Weather {

    private String id;


    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Weather() {

    }
}
