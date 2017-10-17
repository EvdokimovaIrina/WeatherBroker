package weatherBroker.service;

import weatherBroker.exception.WeatherException;
import weatherBroker.model.QueryWeather;

/**
 * Created by IEvdokimova on 17.10.2017.
 */
public interface WeatherServiceJMS {
    void generateWeatherDataInTheCity(String url,String city) throws WeatherException;
    QueryWeather getThisWeatherOutOfTheGueue() throws WeatherException;
}
