package weatherBroker.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import weatherBroker.controller.RestControllerImpl;
import weatherBroker.dao.WeatherDao;
import weatherBroker.exception.WeatherException;
import weatherBroker.model.QueryWeather;
import weatherBroker.service.WeatherServiceDAO;


public class WeatherServiceDAOImpl implements WeatherServiceDAO {
    private static Logger logger = Logger.getLogger(WeatherServiceDAOImpl.class.getName());

    private WeatherDao weatherDao;

    public WeatherServiceDAOImpl() {
    }

    //БД
    @Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
    public void saveObjectToBD(QueryWeather weather) throws WeatherException {
            weatherDao.saveObgectToBD(weather);
    }


    @Transactional(readOnly = true, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public QueryWeather getObjectFromTheBD(String city) throws WeatherException {
          return  weatherDao.getObject(city);
    }


    public String getWeatherXMLfromBD(String city) throws WeatherException {
        QueryWeather queryWeather = getObjectFromTheBD(city);
        ObjectMapper objectMapper = new XmlMapper();
        String xml;
        try {
            xml = objectMapper.writeValueAsString(queryWeather);
        } catch (JsonProcessingException e) {
            throw new WeatherException("Ошибка формирования данных в xml",e);
        }
        return xml;
    }


    public QueryWeather getWeatherJSONfromBD(String city) throws WeatherException {
        return getObjectFromTheBD(city);
    }

    public WeatherDao getWeatherDao() {
        return weatherDao;
    }

    public void setWeatherDao(WeatherDao weatherDao) {
        this.weatherDao = weatherDao;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        WeatherServiceDAOImpl.logger = logger;
    }
}
