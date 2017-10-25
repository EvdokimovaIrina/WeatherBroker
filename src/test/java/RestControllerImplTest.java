import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RestController;
import weatherBroker.controller.RestControllerImpl;
import weatherBroker.controller.eventResult.EventType;
import weatherBroker.controller.eventResult.RestResult;
import weatherBroker.dao.WeatherDao;
import weatherBroker.dao.impl.WeatherDaoImpl;
import weatherBroker.exception.WeatherException;
import weatherBroker.jms.MessageListenerImpl;
import weatherBroker.model.QueryWeather;
import weatherBroker.service.WeatherServiceDAO;
import weatherBroker.service.WeatherServiceJMS;
import weatherBroker.service.impl.WeatherServiceDAOImpl;
import weatherBroker.service.impl.WeatherServiceJMSImpl;

import static org.mockito.Mockito.*;

/*@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration*/
public class RestControllerImplTest {

    @Test
    public void setWeatherTest() throws Exception {
        String city = "Saratov";
        String url = "https://query.yahooapis.com/v1/public/yql?q=select * from weather.forecast where woeid in " +
                "(select woeid from geo.places(1) where text =\"" + city + "\")&format=json";
        QueryWeather weather = new QueryWeather();
        weather.setCity(city);
        RestControllerImpl restControllerImpl = new RestControllerImpl();


        WeatherServiceDAOImpl weatherServiceDAO = mock(WeatherServiceDAOImpl.class);
        doNothing().when(weatherServiceDAO).saveObjectToBD(weather);


        WeatherServiceJMSImpl weatherServiceJMS = mock(WeatherServiceJMSImpl.class);
        when(weatherServiceJMS.getThisWeatherOutOfTheGueue()).thenReturn(weather);

        MessageListenerImpl messageListener = mock(MessageListenerImpl.class);
        JmsTemplate jmsTemplate = mock(JmsTemplate.class);

        weatherServiceJMS.setJmsTemplate(jmsTemplate);
        weatherServiceJMS.setMessageListener(messageListener);

        restControllerImpl.setWeatherServiceDAO(weatherServiceDAO);
        restControllerImpl.setWeatherServiceJMS(weatherServiceJMS);

        RestResult restResult = new RestResult(EventType.WEATHER, weather);
        Assert.assertEquals("не прошел проверку метод setWeather",restResult,restControllerImpl.setWeather(city));


    }

    @Test
    public void addWeatherToBDTest() throws Exception {
        String city = "Saratov";
        QueryWeather weather = new QueryWeather();
        weather.setCity(city);
        RestControllerImpl restControllerImpl = new RestControllerImpl();


        WeatherServiceDAOImpl weatherServiceDAO = mock(WeatherServiceDAOImpl.class);
        doNothing().when(weatherServiceDAO).saveObjectToBD(weather);


        WeatherServiceJMSImpl weatherServiceJMS = mock(WeatherServiceJMSImpl.class);
        when(weatherServiceJMS.getThisWeatherOutOfTheGueue()).thenReturn(weather);


        restControllerImpl.setWeatherServiceDAO(weatherServiceDAO);
        restControllerImpl.setWeatherServiceJMS(weatherServiceJMS);

        Assert.assertEquals("Не прошла проверка addWeatherToBD",weather,restControllerImpl.addWeatherToBD());
    }
}
