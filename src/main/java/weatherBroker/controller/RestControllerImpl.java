package weatherBroker.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import weatherBroker.controller.eventResult.EventType;
import weatherBroker.controller.eventResult.RestResult;
import weatherBroker.dao.impl.WeatherDaoImpl;
import weatherBroker.exception.WeatherException;
import weatherBroker.model.QueryWeather;
import weatherBroker.service.WeatherServiceDAO;
import weatherBroker.service.WeatherServiceJMS;


@RestController("restController")
@RequestMapping(value = "/rest")
public class RestControllerImpl {
    private static Logger logger = Logger.getLogger(RestControllerImpl.class.getName());

    @Autowired()
    private WeatherServiceDAO weatherServiceDAO;

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
            logger.error("Ошибка ", e);
            new RestResult(EventType.ERROR, e);
        }
        logger.debug("Получены данные погоды города  " + weather.getCity());
        return new RestResult(EventType.WEATHER, weather);
    }

    public QueryWeather addWeatherToBD() {
        QueryWeather weather = null;
        try {
            weather = weatherServiceJMS.getThisWeatherOutOfTheGueue();
            weatherServiceDAO.saveObjectToBD(weather);

        } catch (WeatherException e) {
            logger.error("Ошибка ", e);
            new RestResult(EventType.ERROR, e);
        }
      return weather;
    }

    @RequestMapping(value = "/weather/{format}", method = RequestMethod.GET)
    public RestResult getWeather(@PathVariable(value = "format") String format, @RequestParam(value = "city") String city) {

        try {
            switch (format) {
                case "xml":
                    String str = weatherServiceDAO.getWeatherXMLfromBD(city);
                    logger.debug("Получены данные погоды города  " + city);
                    return new RestResult(EventType.WEATHER, str);

                case "json":
                    QueryWeather weather = weatherServiceDAO.getWeatherJSONfromBD(city);
                    logger.debug("Получены данные погоды города  " + city);
                    return new RestResult(EventType.WEATHER, weather);
                default:
                    new RestResult(EventType.ERROR, "не верно указан формат возвращаемого значения");
            }
        } catch (WeatherException e) {
            logger.error("Ошибка ", e);
            new RestResult(EventType.ERROR, e);
        }

        return new RestResult(EventType.ERROR, "Ошибка данных");
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        RestControllerImpl.logger = logger;
    }

    public WeatherServiceDAO getWeatherServiceDAO() {
        return weatherServiceDAO;
    }

    public void setWeatherServiceDAO(WeatherServiceDAO weatherServiceDAO) {
        this.weatherServiceDAO = weatherServiceDAO;
    }

    public WeatherServiceJMS getWeatherServiceJMS() {
        return weatherServiceJMS;
    }

    public void setWeatherServiceJMS(WeatherServiceJMS weatherServiceJMS) {
        this.weatherServiceJMS = weatherServiceJMS;
    }
}
