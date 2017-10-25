package weatherBroker.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import weatherBroker.dao.WeatherDao;
import weatherBroker.exception.WeatherException;
import weatherBroker.model.QueryWeather;



public class WeatherDaoImpl implements WeatherDao{

    private SessionFactory sessionFactory;
    private static Logger logger = Logger.getLogger(WeatherDaoImpl.class.getName());

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = {java.lang.Exception.class})
    public QueryWeather getObject(String city) throws WeatherException {
        QueryWeather weather=null;
        try {
            Session session = sessionFactory.getCurrentSession();

            Criteria weatherCriteria = session.createCriteria(QueryWeather.class);
            weatherCriteria.add(Restrictions.eq("city", city));
            weather = (QueryWeather) weatherCriteria.uniqueResult();
            return weather;
        } catch (Exception e) {
            logger.error("Ошибка при получении данных ", e);
            throw new WeatherException("Ошибка при получении данных ", e);
        }
    }

    public void saveObgectToBD(QueryWeather weather) throws WeatherException{
        logger.debug("записаны данные погоды для города " +weather.getCity());
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(weather);
        } catch (Exception e) {
            logger.error("Ошибка при записи в БД ", e);
            throw new WeatherException("Ошибка при записи в БД ", e);
        }

    }


    public WeatherDaoImpl() {
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        WeatherDaoImpl.logger = logger;
    }
}
