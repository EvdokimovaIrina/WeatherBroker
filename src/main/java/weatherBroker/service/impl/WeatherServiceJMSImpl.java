package weatherBroker.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.client.RestTemplate;
import weatherBroker.exception.WeatherException;
import weatherBroker.jms.MessageListenerImpl;
import weatherBroker.model.QueryWeather;
import weatherBroker.service.WeatherServiceJMS;


public class WeatherServiceJMSImpl implements WeatherServiceJMS {

    private MessageListenerImpl messageListener;
    private JmsTemplate jmsTemplate;

    public void generateWeatherDataInTheCity(String url,String city) throws WeatherException {
        RestTemplate restTemplate = new RestTemplate();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode;
        QueryWeather weather;
        try {
            jsonNode = restTemplate.getForObject(url, JsonNode.class).get("query");
            weather = mapper.readValue(jsonNode.traverse(),QueryWeather.class);
            weather.setCity(city);
        } catch (Exception e) {
            throw new WeatherException("Ошибка получения данных с погодного ресурса",e);
        }

        sendTheWeatherToTheQueue(weather);
    }

    public void sendTheWeatherToTheQueue(QueryWeather weather) throws WeatherException {
        synchronized (this) {
            jmsTemplate.convertAndSend(weather);
        }

    }

    public QueryWeather getThisWeatherOutOfTheGueue() throws WeatherException {
        QueryWeather queryWeather;
        synchronized (this) {
            queryWeather = messageListener.receiveMessage();
        }
        return queryWeather;
    }


    public MessageListenerImpl getMessageListener() {
        return messageListener;
    }

    public void setMessageListener(MessageListenerImpl messageListener) {
        this.messageListener = messageListener;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }


}
