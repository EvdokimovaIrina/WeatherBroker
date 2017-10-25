package weatherBroker.service;

import weatherBroker.exception.WeatherException;
import weatherBroker.model.QueryWeather;


public interface WeatherServiceJMS {
    void generateWeatherDataInTheCity(String url,String city) throws WeatherException;
    QueryWeather getThisWeatherOutOfTheGueue() throws WeatherException;
}
