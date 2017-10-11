package weatherBroker.service;

import weatherBroker.exception.WeatherException;
import weatherBroker.model.QueryWeather;

public interface WeatherService {
    void generateWeatherDataInTheCity(String url) throws WeatherException;
    QueryWeather getThisWeatherOutOfTheGueue() throws WeatherException;
}
