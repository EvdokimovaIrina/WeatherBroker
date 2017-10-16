package weatherBroker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "weather")
public class QueryWeather implements Serializable{
    private String id;
    private String city;
    private String count;
    private Date created;
    private String lang;
    private Results results;

    public QueryWeather() {
    }

    @Id
    @AttributeOverrides({
            @AttributeOverride(name = "city",
                    column = @Column(name="city")),
            @AttributeOverride(name = "created",
                    column = @Column(name="created"))
    })
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
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
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
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


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="results_id")
    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }
}
