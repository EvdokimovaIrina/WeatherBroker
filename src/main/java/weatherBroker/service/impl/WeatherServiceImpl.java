package weatherBroker.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import weatherBroker.dao.WeatherDao;
import weatherBroker.exception.WeatherException;
import weatherBroker.jms.JmsMessageSender;
import weatherBroker.jms.MessageListenerImpl;
import weatherBroker.model.QueryWeather;
import weatherBroker.service.WeatherService;
import javax.jms.JMSException;


public class WeatherServiceImpl implements WeatherService {

    private JmsMessageSender jmsMessageSender;

    private MessageListenerImpl messageListener;

    private WeatherDao weatherDao;

    public WeatherServiceImpl() {
    }

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
        try {
            synchronized (this) {
                jmsMessageSender.sendMessage(weather);
            }
        } catch (JMSException e) {
            throw new WeatherException("Ошибка при отправке объекта в очередь",e);
        }

    }


    public QueryWeather getThisWeatherOutOfTheGueue() throws WeatherException {
        QueryWeather queryWeather;
        synchronized (this) {
            queryWeather = messageListener.receiveMessage();
        }
        return queryWeather;
    }

    @Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
    public void saveObjectToBD(QueryWeather weather) throws WeatherException {
        synchronized (this) {
            weatherDao.saveObgectToBD(weather);
        }
    }

    @Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
    public void saveObject(Object object) throws WeatherException {
        synchronized (this) {
            weatherDao.saveObgect(object);
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public QueryWeather getObjectFromTheBD(String city) throws WeatherException {
        synchronized (this) {
        }

        return null;
    }
    public JmsMessageSender getJmsMessageSender() {
        return jmsMessageSender;
    }

    public void setJmsMessageSender(JmsMessageSender jmsMessageSender) {
        this.jmsMessageSender = jmsMessageSender;
    }

    public MessageListenerImpl getMessageListener() {
        return messageListener;
    }

    public void setMessageListener(MessageListenerImpl messageListener) {
        this.messageListener = messageListener;
    }

    public WeatherDao getWeatherDao() {
        return weatherDao;
    }

    public void setWeatherDao(WeatherDao weatherDao) {
        this.weatherDao = weatherDao;
    }
}
