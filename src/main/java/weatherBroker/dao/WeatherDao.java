package weatherBroker.dao;

import weatherBroker.exception.WeatherException;
import weatherBroker.model.QueryWeather;


public interface WeatherDao {
    void saveObgectToBD(QueryWeather weather) throws WeatherException;
    QueryWeather getObject(String city) throws WeatherException;

}
