package weatherBroker.service;

import weatherBroker.exception.WeatherException;
import weatherBroker.model.QueryWeather;

public interface WeatherService {
    void generateWeatherDataInTheCity(String url,String city) throws WeatherException;
    QueryWeather getThisWeatherOutOfTheGueue() throws WeatherException;
    void saveObjectToBD(QueryWeather weather) throws WeatherException;
    QueryWeather getObjectFromTheBD(String city) throws WeatherException;
}
