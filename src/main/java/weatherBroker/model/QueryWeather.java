package weatherBroker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "weather")
public class QueryWeather implements Serializable{
    private String city;
    private String count;
    private String created;
    private String lang;
    private Results results;

    public QueryWeather() {
    }
    @Id
    @Column(nullable=false)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(nullable=false)
    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Basic
    @Column(nullable=false)
    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Basic
    @Column(nullable=false)
    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }


    @OneToOne
    @JoinColumn(name = "city", referencedColumnName = "weather_id")
    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }
}
