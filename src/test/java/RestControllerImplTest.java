import org.junit.Assert;
import org.junit.Test;

import org.springframework.jms.core.JmsTemplate;

import weatherBroker.controller.RestControllerImpl;
import weatherBroker.controller.eventResult.EventType;
import weatherBroker.controller.eventResult.RestResult;

import weatherBroker.jms.MessageListenerImpl;
import weatherBroker.model.QueryWeather;

import weatherBroker.service.impl.WeatherServiceDAOImpl;
import weatherBroker.service.impl.WeatherServiceJMSImpl;

import static org.mockito.Mockito.*;

/*@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration*/
public class RestControllerImplTest {
    String city = "Saratov";


    @Test
    public void setWeatherTest() throws Exception {

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

    @Test
    public void getWeatherXMLTest() throws Exception {
        QueryWeather weather = new QueryWeather();
        weather.setCity(city);
        RestControllerImpl restControllerImpl = new RestControllerImpl();


        WeatherServiceDAOImpl weatherServiceDAO = mock(WeatherServiceDAOImpl.class);
        when(weatherServiceDAO.getWeatherXMLfromBD(city)).thenReturn("Saratov");

        restControllerImpl.setWeatherServiceDAO(weatherServiceDAO);

        RestResult restResult = new RestResult(EventType.WEATHER, city);
        Assert.assertEquals("Не прошла проверка getWeather для xml",restResult,restControllerImpl.getWeather("xml",city));
    }
}
