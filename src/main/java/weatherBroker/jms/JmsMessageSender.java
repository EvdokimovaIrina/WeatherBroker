package weatherBroker.jms;


import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.converter.MessageConverter;
import weatherBroker.model.QueryWeather;

import javax.jms.*;
import java.util.HashMap;
import java.util.Map;

public class JmsMessageSender {

    private JmsTemplate jmsTemplate;
    private Queue queue;
    private MessageConverterImpl messageConverter;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public void simpleSend() {
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("1111");
            }
        });


    }

    public void sendMessage(final QueryWeather queryWeather) {
        /*jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(queryWeather);
            }
        });*/
        this.jmsTemplate.convertAndSend(queryWeather);

    }
}
