package weatherBroker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import weatherBroker.controller.eventResult.EventType;
import weatherBroker.controller.eventResult.FactoryRestResult;
import weatherBroker.controller.eventResult.RestResult;
import weatherBroker.exception.WeatherException;
import weatherBroker.service.WeatherService;



@RestController("restController")
@RequestMapping(value = "/rest")
public class RestControllerImpl {

    @Autowired()
    private WeatherService weatherService;
    @Autowired()
    private FactoryRestResult factoryRestResult;

    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    public RestResult getWeather(@RequestParam(value = "city") String city) {
        String url = "https://query.yahooapis.com/v1/public/yql?q=select * from weather.forecast where woeid in " +
                "(select woeid from geo.places(1) where text =\"" + city + "\")&format=json";

        try {
            weatherService.generateWeatherDataInTheCity(url);
        } catch (WeatherException e) {
            new RestResult(EventType.ERROR, e);
        }
        return new RestResult(EventType.WEATHER, null);
    }
}
