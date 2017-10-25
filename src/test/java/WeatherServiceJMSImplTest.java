import org.junit.Test;
import weatherBroker.service.impl.WeatherServiceJMSImpl;

import static org.junit.Assert.*;

/**
 * Created by IEvdokimova on 19.10.2017.
 */
public class WeatherServiceJMSImplTest {
    @Test
    public void TestGenerateWeatherDataInTheCity() throws Exception {
        WeatherServiceJMSImpl weatherServiceJMS = new WeatherServiceJMSImpl();
        String city = "Saratov";
        String url = "https://query.yahooapis.com/v1/public/yql?q=select * from weather.forecast where woeid in " +
                "(select woeid from geo.places(1) where text =\"" + city + "\")&format=json";
        weatherServiceJMS.generateWeatherDataInTheCity(url,city);

    }

}