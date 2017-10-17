package weatherBroker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import weatherBroker.controller.eventResult.EventType;
import weatherBroker.controller.eventResult.RestResult;
import weatherBroker.exception.WeatherException;
import weatherBroker.model.QueryWeather;
import weatherBroker.service.WeatherServiceDAO;
import weatherBroker.service.WeatherServiceJMS;


@RestController("restController")
@RequestMapping(value = "/rest")
public class RestControllerImpl {

    @Autowired()
    private WeatherServiceDAO weatherService;

    @Autowired()
    private WeatherServiceJMS weatherServiceJMS;


    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    public RestResult setWeather(@RequestParam(value = "city") String city) {
        String url = "https://query.yahooapis.com/v1/public/yql?q=select * from weather.forecast where woeid in " +
                "(select woeid from geo.places(1) where text =\"" + city + "\")&format=json";

        QueryWeather weather = null;
        try {
            weatherServiceJMS.generateWeatherDataInTheCity(url, city);
            weather = addWeatherToBD();
        } catch (WeatherException e) {
            new RestResult(EventType.ERROR, e);
        }

        return new RestResult(EventType.WEATHER, weather);
    }

    public QueryWeather addWeatherToBD() {
        QueryWeather weather = null;
        try {
            weather = weatherServiceJMS.getThisWeatherOutOfTheGueue();
            weatherService.saveObjectToBD(weather);

        } catch (WeatherException e) {
            new RestResult(EventType.ERROR, e);
        }
      return weather;
    }

    @RequestMapping(value = "/weather/{format}", method = RequestMethod.GET)
    public RestResult getWeather(@PathVariable(value = "format") String format, @RequestParam(value = "city") String city) {
        try {
            switch (format) {
                case "xml":
                    return new RestResult(EventType.WEATHER, weatherService.getWeatherXMLfromBD(city));

                case "json":
                    return new RestResult(EventType.WEATHER, weatherService.getWeatherJSONfromBD(city));

                default:
                    new RestResult(EventType.ERROR, "не верно указан формат возвращаемого значения");
            }
        } catch (WeatherException e) {
            new RestResult(EventType.ERROR, e);
        }
        return new RestResult(EventType.ERROR, "Ошибка данных");
    }
}
