
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import weatherBroker.dao.WeatherDao;
import weatherBroker.exception.WeatherException;



@Transactional

public class WeatherDaoImplTest {
    @InjectMocks
    @Autowired
    WeatherDao weatherDao;

    @Test
    void testGetObject(){

    }

}
