package weatherBroker.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import weatherBroker.controller.eventResult.EventType;
import weatherBroker.controller.eventResult.RestResult;
import weatherBroker.exception.WeatherException;
import weatherBroker.jms.JmsMessageSender;
import weatherBroker.model.QueryWeather;
import weatherBroker.model.Weather;
import weatherBroker.service.WeatherService;

import javax.jms.JMSException;

public class WeatherServiceImpl implements WeatherService {
    @Autowired()
    private JmsMessageSender jmsMessageSender;
    public WeatherServiceImpl() {
    }

    public void generateWeatherDataInTheCity(String url) throws WeatherException {
        RestTemplate restTemplate = new RestTemplate();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode;
        QueryWeather weather;
        try {
            jsonNode = restTemplate.getForObject(url, JsonNode.class).get("query");
            weather = mapper.readValue(jsonNode.traverse(),QueryWeather.class);

        } catch (Exception e) {
           throw new WeatherException("Ошибка получения данных с погодного ресурса",e);
        }

        sendTheWeatherToTheQueue(weather);
    }

    public void sendTheWeatherToTheQueue(QueryWeather weather) throws WeatherException {
        try {
            jmsMessageSender.sendMessage(weather);
        } catch (JMSException e) {
            throw new WeatherException("Ошибка при отправке объекта в очередь",e);
        }


    }


}
