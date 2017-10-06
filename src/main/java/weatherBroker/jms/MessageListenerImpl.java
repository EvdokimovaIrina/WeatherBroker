package weatherBroker.jms;


import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import weatherBroker.exception.WeatherException;
import weatherBroker.model.QueryWeather;

import javax.jms.*;

public class MessageListenerImpl implements MessageListener {
    private JmsTemplate jmsTemplate;
    private Queue queue;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public void onMessage(Message message) {

        if (message instanceof TextMessage) {
            try {
                String msg = ((TextMessage) message).getText();
                System.out.println("Received message: " + msg);
            } catch (JMSException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public QueryWeather receiveMessage() throws WeatherException {
        QueryWeather queryWeather;
        try {
            queryWeather = (QueryWeather) this.jmsTemplate.receiveAndConvert();
        } catch (JmsException e){
            throw new WeatherException("Ошибка получения сообщения",e);
        }

        return queryWeather;
    }
}