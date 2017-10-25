package weatherBroker.service;

import weatherBroker.exception.WeatherException;
import weatherBroker.model.QueryWeather;

public interface WeatherServiceDAO {

    void saveObjectToBD(QueryWeather weather) throws WeatherException;
    QueryWeather getObjectFromTheBD(String city) throws WeatherException;
    String getWeatherXMLfromBD(String city) throws WeatherException;
    QueryWeather getWeatherJSONfromBD(String city) throws WeatherException;
}
