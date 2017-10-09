package weatherBroker.service;

import weatherBroker.exception.WeatherException;

public interface WeatherService {
    void generateWeatherDataInTheCity(String url) throws WeatherException;
}
